#include <libparticle.h>
#include <stdio.h>
#include <SensorValues_operations.h>
#include <bitsio/write_bits_buf.h>
#include <Sample_dom2bin.h>
#define size_t uint8_t
#include <sens_types.h>

sens_SSimpSample s;
#include "../../pPart/XMLPRS/prs74.h"
#include "../../pPart/XMLPRS/prs74_set.c"

static int s_recv, s_send;


int include[]={1,1,1,0,1};

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
                size_t data_len;
                uint8_t buf[64];

		p_acl_add_str(p, "svc", (uint8_t *) &svc, sizeof(svc), 0);
		p_acl_add_str(p, "op", (uint8_t *) &op, sizeof(op), 0);
		{
                  int i;

                  memset(&s,0,sizeof(s));

                  for(i=0; i<8 && i<sizeof(include);i++)
                    if(include[i]) PRSAddSensorValueToSend(i);
                }

                {
                  struct WRITER_STRUCT *writer=write_bits_bufwriter_stack_new(buf,sizeof(buf));
                  Sample_dom2bin_run(&s, writer);
                  data_len=write_buf_finish(writer);
                }
		p_acl_add_str(p, "xml", buf, data_len, 0);

		p_describe_pkt(p);
		p_socket_send(s_send, p);

		p_pkt_free(p);
		sleep(1);
	}

}
