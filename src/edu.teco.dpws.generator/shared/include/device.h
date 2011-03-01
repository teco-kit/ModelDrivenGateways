typedef int (*device_serve_requests_ptr)(struct soap *);

typedef ssize_t (* device_get_serve_requests_func)(device_serve_requests_ptr **);
typedef int (*device_init_service_func)(struct soap *);
typedef int (*device_setup_hosting_service_func)(struct dpws_s *device, struct soap *service, char *uuid);
typedef int (*device_setup_device_func)(struct dpws_s *device, struct soap *service);
typedef int (*device_set_metadata_device_func)(struct dpws_s *device);
typedef int (*device_set_metadata_model_func)(struct dpws_s *device);
typedef int (*device_set_wsdl_func)(struct dpws_s *device);

#ifdef MODEL
int MODEL(init_service(struct soap *service));
int MODEL(setup_device(struct dpws_s *device, struct soap *service));
int MODEL(set_metadata_device(struct dpws_s *device));
int MODEL(set_metadata_model(struct dpws_s *device));
int MODEL(set_wsdl(struct dpws_s *device));
void MODEL(event(int svc, int op, void* dev, char *buf, size_t len));
int MODEL(setup_hosting_service(struct dpws_s *device,
		struct soap *service, char *uuid));
ssize_t MODEL(get_serve_requests(device_serve_requests_ptr **)) ;
#endif
