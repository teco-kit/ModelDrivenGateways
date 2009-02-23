/* ========================================================================
 *
 *        Filename   : encoder.c
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        05.04.2008 21:43    dy    initial version
 * ========================================================================
 */
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>

#include <libparticle.h>
#include "../bitsio/bits_io.h"
#include "../bitsio/read_bits_buf.h"
#include "encoder_automata.h"

/* ========================================================================
 *
 *
 * ========================================================================*/
int main(void)
{
	int recv,send;
	struct  p_packet *p=NULL;
	recv= p_socket_open(0,0,5555);
	send = p_socket_open(0,0,5556);
	
	struct READER_STRUCT reader;

	while(p=p_socket_recv(recv,send))
	{
		//p_describe_pkt(p);
		
		struct p_acl_tuple *a;
		if(p_acl_findfirst_str( p, "xml", &a )>=0) 
		{
			uint8_t *buff;	
			ssize_t len;
			len=p_acl_get_data(a,&buff);

         read_init(&reader, buff);
         encoder_automata_init(fileno(stdout), (void*)&reader);
	 fflush(stdout);
         encoder_automata_run();
		}
		
		p_pkt_free(p);
	}

}

