#include "stdsoap2.h"
#include <libparticle.h>

#ifndef WIN32
#include <pthread.h>
#endif

/*#include "../../bitsio/read_bits_buf.h"*/
int shutdownFlag = 0; /* Flag indicates system shutdown. */

#ifdef WIN32                    /* The working thread. */
DWORD workerid;
HANDLE worker;
#else
pthread_t worker;
#endif

static char buffer1[1024];
static char buffer2[1024];
static void *device;

static char * global_packet_buffer = buffer1;
int curr_buf = 0;


char * get_global_packet_buf(void)
{
	return global_packet_buffer;
}

void *
event_worker_loop() {

  /*struct READER_STRUCT reader;*/
	int recv, send;
	struct p_packet *p = NULL;
	recv = p_socket_open(0, 0, 5555);
	send = p_socket_open(0, 0, 5556);

	printf("Starting working loop...\n");

	while (p = p_socket_recv(recv, send)) {
		struct p_acl_tuple *a;

		if (shutdownFlag)
			return;

		if (p_acl_findfirst_str(p, "xml", &a) >= 0) {
			uint8_t *buff;
			ssize_t len;
			len = p_acl_get_data(a, &buff);

			deliver_event(device, buff);
			/*
        		read_init(&reader, buff);
	                encoder_automata_init(fileno(stdout), &reader);
		        encoder_automata_run();
			*/

			
			if (curr_buf == 0)
			{
				memcpy(buffer2, buff, len);
				curr_buf = 1;
				global_packet_buffer = buffer2;
			}
			else
			{
				memcpy(buffer1, buff, len);
				curr_buf = 0;
				global_packet_buffer = buffer1;
			}

		}

		p_pkt_free(p);
	        /*sleep(15);*/
	}



	printf("Leaving working loop...\n");
}

int event_worker_init(void *_device) {
	device = _device;

#ifdef WIN32
	worker =
	CreateThread (NULL, 0, (DWORD WINAPI) event_worker_loop, NULL, 0,
			&workerid);
	if (worker != NULL)
#else
	if (pthread_create(&worker, NULL, event_worker_loop, NULL) == 0)
#endif
	{
		return SOAP_OK;
	} else {
		return SOAP_ERR;
	}
}

int event_worker_shutdown() {
	shutdownFlag = 1;
#ifdef WIN32
	WaitForSingleObject (worker, INFINITE);
#else
	pthread_join(worker, NULL);
#endif
}
