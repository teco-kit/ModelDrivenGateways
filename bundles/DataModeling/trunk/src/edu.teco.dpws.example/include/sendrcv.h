#include <soap.h>
#include <dpws_device.h>

void send_buf(struct dpws_s *device,uint16_t service_id,uint8_t op_id, struct soap* msg, u_char* buf ,ssize_t len);
char *rcv_buf(struct dpws_s *device,uint16_t service_id,uint8_t op_id, struct soap* msg);
