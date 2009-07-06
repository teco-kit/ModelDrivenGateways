#include <soap.h>
#include <dpws_device.h>

void send_buf(struct dpws_s *device,int service_id,int op_id, struct soap* msg, u_char* buf ,ssize_t len);
char *rcv_buf(struct dpws_s *device,int service_id,int op_id, struct soap* msg);
