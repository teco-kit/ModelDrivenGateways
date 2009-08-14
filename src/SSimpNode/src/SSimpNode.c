#include <libparticle.h>
#include <stdio.h>
#include <SensorValues_operations.h>
#include <bitsio/write_bits_buf.h>
#include <Sample_dom2bin.h>
#include <sens_types.h>

static int s_recv, s_send;

int main() {
	if (0 > (s_recv = p_socket_open(0, 0, 5556)) || 0 > (s_send
			= p_socket_open(0, 0, 5555))) {
		printf("cannot bind libparticle  ...\n");
		return -1;
	}
	p_socket_set_option(s_recv, SOCKET_BLOCKING, 0);
	printf("listening for particles on:\n");
	p_describe_socket(s_recv);
	printf("sending to particles on:\n");
	p_describe_socket(s_send);
	fflush(stdout);

	for (;;) {
		struct p_packet *p = p_pkt_alloc();
		uint16_t svc = 0;
		uint8_t op = OP_SensorValues_SensorValuesEvent;
		p_acl_add_str(p, "svc", (uint8_t *) &svc, sizeof(svc), 0);
		p_acl_add_str(p, "op", (uint8_t *) &op, sizeof(op), 0);
		{

			sens_SSimpSample s = { };

			{
				sens_ADXL210Sample *accl = sens_SSimpSample_add_accelleration(
						&s);
				accl->x = 42;
				accl->y = -21;
				accl->z = 10;
			}

			{
				sens_DateTime *t = sens_SSimpSample_add_timeStamp(&s);
				t->tv_sec = time(NULL);
			}

			u_char sendbuf[256];
			struct WRITER_STRUCT* writer =
					write_bits_bufwriter_stack_new(sendbuf);

			Sample_dom2bin_run(&s, writer);
			{
				ssize_t len = write_buf_finish(writer);
				p_acl_add_str(p, "xml", (uint8_t *) &sendbuf, len, 0);
			}

		}

		p_describe_pkt(p);
		p_socket_send(s_send, p);

		p_pkt_free(p);
		sleep(5);
	}

}
