#include <ws4d-gSOAP/dpwsH.h>
#include <ws4d-gSOAP/dpws_device.h>
#include <stdio.h>
#include <dpws_append_wsdl.h>



static struct Namespace namespaces[] = {

{ "SOAP-ENV", "http://www.w3.org/2003/05/soap-envelope",
		"http://www.w3.org/2002/06/soap-envelope" },

{ "xsi", "http://www.w3.org/2001/XMLSchema-instance",
		"http://www.w3.org/*/XMLSchema-instance", NULL }, { "xsd",
		"http://www.w3.org/2001/XMLSchema", "http://www.w3.org/*/XMLSchema",
		NULL },

{ "acs", "http://www.teco.edu/AccelerationService", NULL, NULL },

{ NULL, NULL, NULL, NULL } };

void AccelModel_init_service(struct soap *service) {
	soap_init(service);
#ifdef DEBUG
	soap_omode (service, SOAP_XML_INDENT);
#endif
	soap_set_namespaces(service, namespaces);
}

typedef int (*serve_requests_ptr)(struct soap *);

extern int AccelerationService_serve_request(struct soap * soap);

static serve_requests_ptr serve_requests[] = SOAP_SERVE_SET(
		AccelerationService_serve_request);

serve_requests_ptr *AccelModel_get_serve_requests() {
	return serve_requests;
}

int AccelModel_setup_hosting_service(struct dpws_s *device,
		struct soap *service, char *uuid) {
	int ret = 0;

	ret = dpws_add_hosting_service(device, service, 0, "AccelerationDevice", uuid,
			100, 0);

	if (ret != WS4D_OK) {
		fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
				__LINE__);
		return ret;
	}

	{
		struct ws4d_qname *service_type = ws4d_qname_alloc(1,
				&device->alloc_list);
		service_type->ns = ws4d_strdup("http://www.teco.edu/AccelerationService",
				&device->alloc_list);
		service_type->name
				= ws4d_strdup("AccelerationDeviceType", &device->alloc_list);
		if (0 != (ret = dpws_add_type(device, service_type))) {
			fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
					__LINE__);
			return ret;
		}
	}

	return WS4D_OK;

}

int AccelModel_setup_device(struct dpws_s *device, struct soap *service) {

	{
		char uri[DPWS_URI_MAX_LEN] = "http://host:0/";
		int ret;

		strcat(uri, device->hosting_addr);
		strcat(uri, "-1"); //TODO: every service gets an unique id!

		struct ws4d_epr *dpws_service = dpws_service_init(device,
				"AccelerationService");

		if (0 != (ret = dpws_service_bind(device, dpws_service, service, uri,
				DPWS_URI_MAX_LEN, 100))) {
			fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
					__LINE__);
			return ret;
		}

		{
			struct ws4d_qname *service_type = ws4d_qname_alloc(1,
					&device->alloc_list);
			service_type->ns = ws4d_strdup("http://www.teco.edu/AccelerationService",
					&device->alloc_list);
			service_type->name = ws4d_strdup("AccelerationService",
					&device->alloc_list);
			if (0 != (ret = dpws_service_add_type(dpws_service, service_type))) {
				fprintf(stderr, "%s:%d:Can't init device and services\n",
						__FILE__, __LINE__);
				return ret;
			}
		}

		if (0 != (ret = dpws_service_set_wsdl(dpws_service, "AccelerationService"))) {
			fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
					__LINE__);
			return ret;
		}

		if (0 != (ret = dpws_add_hosted_service(device, dpws_service, uri,
				DPWS_URI_MAX_LEN))) {
			fprintf(stderr, "%s:%d:Can't init device and services\n", __FILE__,
					__LINE__);
			return ret;
		}

	}

	return WS4D_OK;
}

int AccelModel_set_metadata_device(struct dpws_s *device) {
	/*set_metadata_ThisDevice*/
	struct ws4d_thisDevice *_device = dpws_change_thisdevice(device);
	static const ws4d_device_FriendlyName_var( friendlyName) = {
			ws4d_locstring_init_string("de", "Beschleunigungsdaten Service"),

			ws4d_locstring_init_string("en", "Acceleration data Service"), };

	ws4d_device_set_FriendlyName(_device, friendlyName, 2);
	ws4d_device_set_FirmwareVersion(_device, "Version 0.1");
	ws4d_device_set_SerialNumber(_device,
			"1234567f-c23f-4d91-84ba-5555555555556");
	return WS4D_OK;
}

int AccelModel_set_metadata_model(struct dpws_s *device) { /*set_metadata_ThisModel*/
	struct ws4d_thisModel *model = dpws_change_thismodel(device);
	static const ws4d_model_Name_var( name) = { ws4d_locstring_init_string(
			"de", "AccelerationDevice Sensorknoten"),

	ws4d_locstring_init_string("en", "AccelerationDevice Sensorknoten"), };
	static const ws4d_model_Manufacturer_var
			( manufacturer) =
					{ ws4d_locstring_init_string("de",
							"TECO (Universität Karlsruhe)"),

					ws4d_locstring_init_string("en",
							"TECO (University of Karlsruhe)"), };

	ws4d_model_set_Manufacturer(model, manufacturer, 2);
	ws4d_model_set_ManufacturerUrl(model, "http://www.teco.edu");
	ws4d_model_set_Name(model, name, 2);
	ws4d_model_set_Number(model, "1.0");
	ws4d_model_set_Url(model, "http://www.teco.edu");
	return WS4D_OK;
}

int AccelModel_set_wsdl(struct dpws_s *device) {

	dpws_append_wsdl(
			device,
			"AccelerationService",
			"<wsdl:definitions xmlns:wsoap12=\"http://schemas.xmlsoap.org/wsdl/soap12/\"\n"
				"	xmlns:tns=\"http://www.teco.edu/AccelerationService\" xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\"\n"
				"\n"
				"	xmlns:wsa_=\"http://www.w3.org/2006/05/addressing/wsdl\" xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\"\n"
				"\n"
				"	xmlns:wse=\"http://schemas.xmlsoap.org/ws/2004/08/eventing\" xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap12/\"\n"
				"\n"
				"	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" name=\"AccelerationService\"\n"
				"	targetNamespace=\"http://www.teco.edu/AccelerationService\">\n"
				"\n"
				"	<wsdl:types>\n"
				"		<schema xmlns=\"http://www.w3.org/2001/XMLSchema\"\n"
				"			elementFormDefault=\"qualified\"\n"
				"			targetNamespace=\"http://www.teco.edu/AccelerationService\">	\n"
				"			<annotation>\n"
				"				<appinfo>\n"
				"					<UnitSet xmlns=\"urn:oasis:names:tc:unitsml:schema:xsd:UnitsMLSchema-0.9.18\">\n"
				"					<Unit\n"
				"									xmlns=\"urn:oasis:names:tc:unitsml:schema:xsd:UnitsMLSchema-0.9.18\"\n"
				"									xml:id=\"U_mm.s-2\">\n"
				"									<UnitSystem name=\"SI\"\n"
				"										type=\"SI_derived\" xml:lang=\"en-US\" />\n"
				"									<UnitName xml:lang=\"en-US\">\n"
				"										millimeter per second squared\n"
				"									</UnitName>\n"
				"									<UnitSymbol type=\"HTML\">\n"
				"										mm &#183; s\n"
				"										<sup>-2</sup>\n"
				"									</UnitSymbol>\n"
				"									<RootUnits>\n"
				"										<EnumeratedRootUnit unit=\"meter\"\n"
				"											prefix=\"m\" />\n"
				"										<EnumeratedRootUnit\n"
				"											unit=\"second\" powerNumerator=\"-2\" />\n"
				"									</RootUnits>\n"
				"								</Unit>\n"
				"					</UnitSet>\n"
				"				</appinfo>\n"
				"			</annotation>\n"
				"			<complexType name=\"ADXL210_Sample\">\n"
				"				<annotation>\n"
				"					<documentation>\n"
				"						Analog Devices ADXL210 Low-Cost Â±10 g Dual-Axis\n"
				"						Accelerometer with Duty Cycle\n"
				"\n"
				"						(z-output from optional orthogonally mounted\n"
				"						second device)\n"
				"\n"
				"\n"
				"\n"
				"						http://www.analog.com/static/imported-files/data_sheets_obsolete/OBSOLETE%20WATERMARK/ADXL210.pdf\n"
				"					</documentation>\n"
				"				</annotation>\n"
				"				<sequence>\n"
				"					<element name=\"x\" type=\"float\">\n"
				"						<annotation>\n"
				"							<appinfo>\n"
				"								<Quantity\n"
				"									xmlns=\"urn:oasis:names:tc:unitsml:schema:xsd:UnitsMLSchema-0.9.18\">\n"
				"									<QuantityName>acceleration</QuantityName>\n"
				"									<UnitReference>#U_mm.s-2</UnitReference>\n"
				"								</Quantity>\n"
				"							</appinfo>\n"
				"						</annotation>\n"
				"					</element>\n"
				"					<element name=\"y\" type=\"float\">\n"
				"					</element>\n"
				"					<element name=\"z\" type=\"float\">\n"
				"					</element>\n"
				"				</sequence>\n"
				"			</complexType>\n"
				"\n"
				"			<complexType name=\"AccelerationSample\">\n"
				"				<annotation>\n"
				"					<documentation>\n"
				"						Acceleration Sample\n"
				"					</documentation>\n"
				"				</annotation>\n"
				"\n"
				"				<sequence>\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\"\n"
				"						name=\"TimeStamp\" type=\"dateTime\" />\n"
				"					<element maxOccurs=\"1\" minOccurs=\"0\"\n"
				"						name=\"Acceleration\" type=\"tns:ADXL210_Sample\">\n"
				"					</element>\n"
				"				</sequence>\n"
				"\n"
				"			</complexType>\n"
				"\n"
				"			<element name=\"Sample\" type=\"tns:AccelerationSample\" />\n"
				"\n"
				"		</schema>\n"
				"	</wsdl:types>\n"
				"\n"
				"	<wsdl:message name=\"AccelerationServiceEventMessageOut\">\n"
				"		<wsdl:part name=\"event\" element=\"tns:Sample\" />\n"
				"	</wsdl:message>\n"
				"\n"
				"	<wsdl:portType name=\"AccelerationService\" wse:EventSource=\"true\">\n"
				"		<wsdl:operation name=\"AccelerationServiceEvent\">\n"
				"			<wsdl:output message=\"tns:AccelerationServiceEventMessageOut\"\n"
				"				wsa:Action=\"http://www.teco.edu/AccelerationService/AccelerationServiceEventOut\" />\n"
				"		</wsdl:operation>\n"
				"	</wsdl:portType>\n"
				"\n"
				"	<wsdl:binding name=\"AccelerationServiceSOAP\" type=\"tns:AccelerationService\">\n"
				"		<soap:binding style=\"document\"\n"
				"			transport=\"http://schemas.xmlsoap.org/soap/http\" />\n"
				"		<wsdl:operation name=\"GetAccelerationService\">\n"
				"			<wsdl:input>\n"
				"				<soap:body use=\"literal\" />\n"
				"			</wsdl:input>\n"
				"			<wsdl:output>\n"
				"				<soap:body use=\"literal\" />\n"
				"			</wsdl:output>\n"
				"		</wsdl:operation>\n"
				"		<wsdl:operation name=\"AccelerationServiceEvent\">\n"
				"			<wsdl:output>\n"
				"				<soap:body use=\"literal\" />\n"
				"			</wsdl:output>\n"
				"		</wsdl:operation>\n"
				"\n"
				"	<wsdl:service name=\"AccelerationService\">\n"
				"		<wsdl:port name=\"AccelerationService\" binding=\"tns:AccelerationServiceSOAP\">\n"
				"			<wsoap12:address location=\"http://localhost:8080\" />\n"
				"		</wsdl:port>\n"
				"	</wsdl:service>\n"
				"</wsdl:definitions>\n");

	return WS4D_OK;
}

void AccelModel_event(int svc, int op, void* dev, char *buf, size_t len) {
	switch (svc) {

	case 0: {
		extern void AccelerationService_event(int, void *, char *, size_t);
		AccelerationService_event(op, dev, buf, len);
		break;
	}
	}
}

