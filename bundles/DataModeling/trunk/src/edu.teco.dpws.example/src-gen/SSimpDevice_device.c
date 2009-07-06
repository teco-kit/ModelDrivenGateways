/* Generated file */

//#include "SSimpDevice.nsmap"
#include <dpws_device.h>

#include <signal.h>

static struct Namespace namespaces[] = { { "SOAP-ENV",
		"http://www.w3.org/2003/05/soap-envelope",
		"http://www.w3.org/2003/05/soap-envelope", NULL }, { "SOAP-ENC",
		"http://www.w3.org/2003/05/soap-encoding",
		"http://www.w3.org/2003/05/soap-encoding", NULL }, { "xsi",
		"http://www.w3.org/2001/XMLSchema-instance",
		"http://www.w3.org/*/XMLSchema-instance", NULL }, { "xsd",
		"http://www.w3.org/2001/XMLSchema", "http://www.w3.org/*/XMLSchema",
		NULL }, { "wsdp", "http://schemas.xmlsoap.org/ws/2006/02/devprof",
		NULL, NULL }, { "wsa",
		"http://schemas.xmlsoap.org/ws/2004/08/addressing", NULL, NULL },

{ "sens", "http://www.teco.edu/SensorValues", NULL, NULL },

{ NULL, NULL, NULL, NULL } };

#include <bitsio/read_bits_buf.h>
#include "event_worker.h"
#include "../src/wsdl.h"

struct soap service;
struct dpws_s device;

void service_exit() {
#ifdef DEBUG
	printf ("\nSensor Monitor: shutting down...\n");
#endif

	event_worker_shutdown();

	dpws_deactivate_hosting_service(&device);
	soap_done(&service);
	dpws_done(&device);

	exit(0);
}

int main(int argc, char **argv) {
#ifndef WIN32
	struct sigaction sa;
#endif
	char *interface = NULL;
	char *uuid = NULL;

	/* parsing command line options */
	while (argc > 1) {
		if (argv[1][0] == '-') {
			char *option = &argv[1][1];

			switch (option[0]) {
			case 'i': /* set interface with option -i */
				if (strlen(option) > 2) {
					++option;
					interface = option;
				} else {
					--argc;
					++argv;
					interface = argv[1];
				}
#ifdef DEBUG
				printf ("\nSensor Monitor: Set interface to \"%s\"\n", interface);
#endif
				break;
			case 'u': /* set id with option -u */
				if (strlen(option) > 2) {
					++option;
					uuid = option;
				} else {
					--argc;
					++argv;
					uuid = argv[1];
				}
#ifdef DEBUG
				printf ("\nSensor Monitor: Set uuid to \"%s\"\n", uuid);
#endif
				break;
			default:
				fprintf(stderr, "\nSensor Monitor: Bad option %s\n", argv[1]);
				printf("\n%s -i [interface address] -u urn:uuid[uuid]\n",
						argv[0]);
				exit(1);
			}
		}
		--argc;
		++argv;
	}

	if (interface == NULL) {
		fprintf(stderr,
				"\nSensor Monitor: No interface addrss was specified!\n");
		exit(1);
	}

	/* initialize soap handle */
	soap_init(&service);
#ifdef DEBUG
	soap_omode (&service, SOAP_XML_INDENT);
#endif
	soap_set_namespaces(&service, namespaces);

	/* initialize device and services */

	if (dpws_init(&device, interface)) {
		fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
				__LINE__);
		dpws_done(&device);
		exit(1);
	}

	{
		int ret = 0;

		ret = dpws_add_hosting_service(&device, &service, 0, "SSimpDevice",
				uuid, 100, 0);

		if (ret != WS4D_OK) {
			fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
					__LINE__);
			dpws_done(&device);
			exit(1);
		}

		{
			struct ws4d_qname *service_type = ws4d_qname_alloc(1,
					&device.alloc_list);
			service_type->ns = ws4d_strdup("http://www.teco.edu/SensorValues",
					&device.alloc_list);
			service_type->name = ws4d_strdup("SSimpDeviceType",
					&device.alloc_list);
			if (dpws_add_type(&device, service_type)) {
				fprintf(stderr, "%s:%d:Can't init device and services\n",
						__FILE__, __LINE__);
				dpws_done(&device);
				exit(1);
			}
		}

	}

	{
		char uri[DPWS_URI_MAX_LEN] = "http://host:0/";
		struct ws4d_epr *dpws_service = dpws_service_init(&device,
				"SensorValues");

		if (dpws_service_bind(&device, dpws_service, &service, uri,
				DPWS_URI_MAX_LEN, 100)) {
			fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
					__LINE__);
			dpws_done(&device);
			exit(1);
		}

		{
			struct ws4d_qname *service_type = ws4d_qname_alloc(1,
					&device.alloc_list);
			service_type->ns = ws4d_strdup("http://www.teco.edu/SensorValues",
					&device.alloc_list);
			service_type->name
					= ws4d_strdup("SensorValues", &device.alloc_list);
			if (dpws_service_add_type(dpws_service, service_type)) {
				fprintf(stderr, "%s:%d:Can't init device and services\n",
						__FILE__, __LINE__);
				dpws_done(&device);
				exit(1);
			}
		}

		if (dpws_service_set_wsdl(dpws_service, "SensorValues")) {
			fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
					__LINE__);
			dpws_done(&device);
			exit(1);
		}

		if (dpws_add_hosted_service(&device, dpws_service, uri,
				DPWS_URI_MAX_LEN)) {
			fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
					__LINE__);
			dpws_done(&device);
			exit(1);
		}
	}

	/* Set Metadata */

	{
		/*set_metadata_ThisDevice*/
		struct ws4d_thisDevice *_device = dpws_change_thisdevice(&device);
		const ws4d_device_FriendlyName_var
				( friendlyName) = { ws4d_locstring_init_string("de",
						"Sensorüberwachungsdienst"),

				ws4d_locstring_init_string("en", "Sensor Monitoring Service"), };

		ws4d_device_set_FriendlyName(_device, friendlyName, 2);
		ws4d_device_set_FirmwareVersion(_device, "Version 0.1");
		ws4d_device_set_SerialNumber(_device,
				"1234567f-c23f-4d91-84ba-555555555555");
	}

	;

	{ /*set_metadata_ThisModel*/
		struct ws4d_thisModel *model = dpws_change_thismodel(&device);
		const ws4d_model_Name_var( name) = { ws4d_locstring_init_string("de",
				"SSimp Sensorknoten"),

		ws4d_locstring_init_string("en", "SSimp Sensorknoten"), };
		const ws4d_model_Manufacturer_var
				( manufacturer) = { ws4d_locstring_init_string("de",
						"TECO (Universität Karlsruhe)"),

				ws4d_locstring_init_string("en",
						"TECO (University of Karlsruhe)"), };

		ws4d_model_set_Manufacturer(model, manufacturer, 2);
		ws4d_model_set_ManufacturerUrl(model, "http://www.teco.edu");
		ws4d_model_set_Name(model, name, 2);
		ws4d_model_set_Number(model, "1.0");
		ws4d_model_set_Url(model, "http://www.teco.edu");
	};
	dpws_append_wsdl(
			&device,
			"SensorValues",
			"<wsdl:definitions xmlns:wsoap12=\"http://schemas.xmlsoap.org/wsdl/soap12/\"\n"
				"	xmlns:tns=\"http://www.teco.edu/SensorValues\" xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\"\n"
				"	xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\" xmlns:wse=\"http://schemas.xmlsoap.org/ws/2004/08/eventing\"\n"
				"    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n"
				"	name=\"SensorValues\" targetNamespace=\"http://www.teco.edu/SensorValues\"\n"
				"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
				"\n"
				"	<wsdl:types>\n"
				"		<schema xmlns=\"http://www.w3.org/2001/XMLSchema\"\n"
				"			elementFormDefault=\"qualified\" targetNamespace=\"http://www.teco.edu/SensorValues\">\n"
				"			<complexType name=\"GravityType\">\n"
				"				<sequence>\n"
				"					<element name=\"AcclValue\" type=\"short\" />\n"
				"					<element name=\"AcclIdx\" type=\"unsignedByte\" />\n"
				"				</sequence>\n"
				"			</complexType>\n"
				"			<complexType name=\"LightType\">\n"
				"				<sequence>\n"
				"					<element name=\"LightValue\" type=\"unsignedByte\" />\n"
				"					<element name=\"LightIdx\" type=\"unsignedByte\" />\n"
				"				</sequence>\n"
				"			</complexType>\n"
				"			<complexType name=\"ForceType\">\n"
				"				<sequence>\n"
				"					<element name=\"ForceValue\" type=\"unsignedByte\" />\n"
				"					<element name=\"ForceIdx\" type=\"unsignedByte\" />\n"
				"				</sequence>\n"
				"			</complexType>\n"
				"			<complexType name=\"TempType\">\n"
				"				<sequence>\n"
				"					<element name=\"TempValue\" type=\"byte\" />\n"
				"					<element name=\"TempIdx\" type=\"unsignedByte\" />\n"
				"				</sequence>\n"
				"			</complexType>\n"
				"			<complexType name=\"SensorValuesType\">\n"
				"				<sequence>\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"AcclX\" type=\"tns:GravityType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"AcclY\" type=\"tns:GravityType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"AcclZ\" type=\"tns:GravityType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Audio\" type=\"unsignedByte\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Light\" type=\"tns:LightType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"AmbientLight\"\n"
				"						type=\"tns:LightType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Force\" type=\"tns:ForceType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Temperature\"\n"
				"						type=\"tns:TempType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Voltage\" type=\"unsignedShort\" />\n"
				"				</sequence>\n"
				"			</complexType>\n"
				"			<element name=\"SensorValues\" type=\"tns:SensorValuesType\" />\n"
				"			\n"
				"			<complexType name=\"SensorConfigurationType\">\n"
				"				<sequence>\n"
				"					<element name=\"sensors\" type=\"tns:SensorSelectionType\" />\n"
				"				</sequence>\n"
				"			</complexType>\n"
				"			\n"
				"			<complexType name=\"GravitySensorConfigType\">\n"
				"			<sequence>\n"
				"			  <element maxOccurs=\"1\" minOccurs=\"1\" name=\"rate\" type=\"short\" />\n"
				"			</sequence>\n"
				"			</complexType>\n"
				"			\n"
				"			<complexType name=\"LightSensorConfigType\">\n"
				"			 <sequence>\n"
				"			  <element maxOccurs=\"1\" minOccurs=\"1\" name=\"rate\" type=\"short\" />\n"
				"			</sequence>\n"
				"			</complexType>\n"
				"			\n"
				"			<complexType name=\"ForceSensorConfigType\">\n"
				"			 <sequence>\n"
				"			  <element maxOccurs=\"1\" minOccurs=\"1\" name=\"rate\" type=\"short\" />\n"
				"			</sequence>\n"
				"			</complexType>\n"
				"			\n"
				"			<complexType name=\"TempSensorConfigType\">\n"
				"			 <sequence>\n"
				"			  <element maxOccurs=\"1\" minOccurs=\"1\" name=\"rate\" type=\"short\" />\n"
				"			</sequence>\n"
				"			</complexType>\n"
				"			\n"
				"			<complexType name=\"AudioSensorConfigType\">\n"
				"			 <sequence>\n"
				"			  <element maxOccurs=\"1\" minOccurs=\"1\" name=\"rate\" type=\"short\" />\n"
				"			</sequence>\n"
				"			</complexType>\n"
				"			\n"
				"			\n"
				"			<complexType name=\"SensorSelectionType\">\n"
				"				<sequence>\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"AcclX\" type=\"tns:GravitySensorConfigType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"AcclY\" type=\"tns:GravitySensorConfigType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"AcclZ\" type=\"tns:GravitySensorConfigType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Audio\" type=\"tns:AudioSensorConfigType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Light\" type=\"tns:LightSensorConfigType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"AmbientLight\"	type=\"tns:LightSensorConfigType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Force\" type=\"tns:ForceSensorConfigType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Temperature\"		type=\"tns:TempSensorConfigType\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\" name=\"Voltage\" type=\"tns:ForceSensorConfigType\" />\n"
				"				</sequence>\n"
				"			</complexType>\n"
				"			\n"
				"			<element name=\"SensorConfiguration\" type=\"tns:SensorConfigurationType\" />	\n"
				"			   \n"
				"        </schema>\n"
				"	</wsdl:types>\n"
				"\n"
				"	<wsdl:message name=\"GetSensorValuesMessageIn\" />\n"
				"	<wsdl:message name=\"GetSensorValuesMessageOut\">\n"
				"		<wsdl:part name=\"parameters\" element=\"tns:SensorValues\" />\n"
				"	</wsdl:message>\n"
				"	<wsdl:message name=\"SensorValuesEventMessageOut\">\n"
				"		<wsdl:part name=\"parameters\" element=\"tns:SensorValues\" />\n"
				"	</wsdl:message>\n"
				"\n"
				"	<wsdl:message name=\"ConfigureSensorsRequest\">\n"
				"		<wsdl:part name=\"parameters\" element=\"tns:SensorConfiguration\"></wsdl:part>\n"
				"	</wsdl:message>\n"
				"	\n"
				"	<wsdl:message name=\"ConfigureSensorsResponse\">\n"
				"	</wsdl:message>\n"
				"	\n"
				"	<wsdl:portType name=\"SensorValuesPort\" wse:EventSource=\"true\">\n"
				"		<wsdl:operation name=\"GetSensorValues\">\n"
				"			<wsdl:input message=\"tns:GetSensorValuesMessageIn\"\n"
				"				wsa:Action=\"http://www.teco.edu/SensorValues/GetSensorValuesIn\" />\n"
				"			<wsdl:output message=\"tns:GetSensorValuesMessageOut\"\n"
				"				wsa:Action=\"http://www.teco.edu/SensorValues/GetSensorValuesOut\" />\n"
				"		</wsdl:operation>\n"
				"		\n"
				"		<wsdl:operation name=\"SensorValuesEvent\">\n"
				"			<wsdl:output message=\"tns:SensorValuesEventMessageOut\"\n"
				"				wsa:Action=\"http://www.teco.edu/SensorValues/SensorValuesEventOut\" />\n"
				"		</wsdl:operation>\n"
				"\n"
				"		<wsdl:operation name=\"ConfigureSensors\">\n"
				"			<wsdl:input message=\"tns:ConfigureSensorsRequest\" wsa:Action=\"http://www.teco.edu/SensorValues/ConfigureSensorsIn\" />\n"
				"			<wsdl:output message=\"tns:ConfigureSensorsResponse\" wsa:Action=\"http://www.teco.edu/SensorValues/ConfigureSensorsOut\" />\n"
				"		</wsdl:operation>\n"
				"		\n"
				"	</wsdl:portType>\n"
				"\n"
				"	<wsdl:binding name=\"SensorValuesSOAP\" type=\"tns:SensorValuesPort\">\n"
				"		<wsoap12:binding style=\"document\"\n"
				"			transport=\"http://schemas.xmlsoap.org/soap/http\" />\n"
				"		<wsdl:operation name=\"GetSensorValues\">\n"
				"			<wsoap12:operation />\n"
				"			<wsdl:input>\n"
				"				<wsoap12:body use=\"literal\" />\n"
				"			</wsdl:input>\n"
				"			<wsdl:output>\n"
				"				<wsoap12:body use=\"literal\" />\n"
				"			</wsdl:output>\n"
				"		</wsdl:operation>\n"
				"	   <wsdl:operation name=\"ConfigureSensors\">\n"
				"			<wsoap12:operation />\n"
				"			<wsdl:input>\n"
				"				<wsoap12:body use=\"literal\" />\n"
				"			</wsdl:input>\n"
				"			<wsdl:output>\n"
				"				<wsoap12:body use=\"literal\" />\n"
				"			</wsdl:output>\n"
				"		</wsdl:operation>\n"
				"		<wsdl:operation name=\"SensorValuesEvent\">\n"
				"			<wsoap12:operation />\n"
				"			<wsdl:output>\n"
				"				<wsoap12:body use=\"literal\" />\n"
				"			</wsdl:output>\n"
				"		</wsdl:operation>\n"
				"\n"
				"	</wsdl:binding>\n"
				"\n"
				"	<wsdl:service name=\"SensorValues\">\n"
				"		<wsdl:port name=\"SensorValuesPort\" binding=\"tns:SensorValuesSOAP\">\n"
				"			<wsoap12:address location=\"\" />\n"
				"		</wsdl:port>\n"
				"	</wsdl:service>\n"
				"</wsdl:definitions>\n");

	/* Update Metadata */
	if (dpws_update_Metadata(&device)) {
		fprintf(stderr, "\nSensor Monitor: Can't init metadata\n");
		dpws_done(&device);
		exit(1);
	}

	/* install signal handler for SIGINT or Ctrl-C */
#ifdef WIN32
	signal (SIGINT, service_exit);
#else
	memset(&sa, 0, sizeof(sa));
	sa.sa_handler = service_exit;
	sigaction(SIGINT, &sa, NULL);
#endif

	/* Tell hosting service to start advertising its hosted services */
	if (dpws_activate_hosting_service(&device)) {
		fprintf(stderr, "\nSensor Monitor: Can't activate device\n");
		dpws_done(&device);
		exit(1);
	}

	/* activate eventing. */
	if (dpws_activate_eventsource(&device, &service)) {
		printf("\nSensor Monitor: Can't activate eventing\n");
		dpws_done(&device);
		exit(0);
	}

	if (event_worker_init(&device)) {
		printf("\nSensor Monitor: Can't init event worker\n");
		dpws_done(&device);
		exit(0);
	}

#ifdef DEBUG
	printf ("\nSensor Monitor: ready to serve... (Ctrl-C for shut down)\n");
#endif

	for (;;) {

		extern int SensorValues_serve_request(struct soap * soap);
		//TODO: pass device
		struct soap *handle = NULL, *soap_set[] = SOAP_HANDLE_SET(&service);
		int (*serve_requests[])(struct soap * soap) =
		SOAP_SERVE_SET (
				SensorValues_serve_request
		);

#ifdef DEBUG
		printf ("\nSensor Monitor: waiting for request\n");
#endif

		/* waiting for new messages */
		handle = dpws_maccept(&device, 100000, 1, soap_set);

		if (handle) {

#ifdef DEBUG
			printf ("\nSensor Monitor: processing request from %s:%d\n",
					inet_ntoa (handle->peer.sin_addr),
					ntohs (handle->peer.sin_port));
#endif

			/* dispatch messages */
			if (dpws_mserve(handle, 1, serve_requests)) {
				soap_print_fault(handle, stderr);
			}

			/* clean up soaps internaly allocated memory */
			soap_end(handle);
		}

		dpws_check_subscriptions(&device);
	}

	return -1;
}
