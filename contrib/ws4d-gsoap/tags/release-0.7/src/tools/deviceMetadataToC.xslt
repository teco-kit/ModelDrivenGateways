<?xml version="1.0" encoding="ISO-8859-1"?>

<!--
 _one line to give the program's name and a brief idea of what it does._
 Copyright (C) 2007  University of Rostock

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License as
 published by the Free Software Foundation; either version 2 of the
 License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 02110-1301 USA.
-->

<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:wsm="http://schemas.xmlsoap.org/ws/2004/09/mex"
		xmlns:wdp="http://schemas.xmlsoap.org/ws/2006/02/devprof"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:wsd="http://schemas.xmlsoap.org/ws/2005/04/discovery"
		xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing"
		xmlns:xsd="http://www.w3.org/2001/XMLSchema">
  <xsl:output method="text"/>

<xsl:param name="nsprefix" select="'dpws'"/>

<xsl:template match="/wsm:Metadata">
#include "dpwsH.h"
#undef DPWS_DEVICE /* Avoids the following compile warning: "DPWS_DEVICE" redefined */
#include "dpws_device.h"
	<xsl:for-each select="wsm:MetadataSection">
		<xsl:if test="@Dialect='http://schemas.xmlsoap.org/ws/2006/02/devprof/ThisDevice'">
 		  <xsl:call-template name="DeviceConstantDefinition"/>
			<xsl:apply-templates/>
		</xsl:if>
	</xsl:for-each>
	<xsl:for-each select="wsm:MetadataSection">
		<xsl:if test="@Dialect='http://schemas.xmlsoap.org/ws/2006/02/devprof/ThisModel'">
 		  <xsl:call-template name="ModelConstantDefinition"/>
			<xsl:apply-templates/>
		</xsl:if>
	</xsl:for-each>
	<xsl:for-each select="wsm:MetadataSection">
		<xsl:if test="@Dialect='http://schemas.xmlsoap.org/ws/2006/02/devprof/Relationship'">
			<xsl:apply-templates/>
		</xsl:if>
	</xsl:for-each>
void <xsl:value-of select="$nsprefix"/>_set_Metadata(struct dpws_s *device)
{
  <xsl:value-of select="$nsprefix"/>_setmetadata_ThisDevice(device);
  <xsl:value-of select="$nsprefix"/>_setmetadata_ThisModel(device);
}
</xsl:template>

<xsl:template name="DeviceConstantDefinition">
/* Device Metadata */
    <xsl:if test="count(wdp:ThisDevice/wdp:FriendlyName) != 0">
const ws4d_device_FriendlyName_var(<xsl:value-of select="$nsprefix"/>_FriendlyName) = {
      <xsl:for-each select="wdp:ThisDevice/wdp:FriendlyName">
	ws4d_locstring_init_string("<xsl:value-of select="@lang"/>", "<xsl:value-of select="text()"/>"),
      </xsl:for-each>
 };
    </xsl:if>

    <xsl:if test="count(wdp:ThisDevice/wdp:FirmwareVersion) != 0">
const ws4d_device_FirmwareVersion_var(<xsl:value-of select="$nsprefix"/>_FirmwareVersion) =
        "<xsl:value-of select="wdp:ThisDevice/wdp:FirmwareVersion"/>";
    </xsl:if>

    <xsl:if test="count(wdp:ThisDevice/wdp:SerialNumber) != 0">
const ws4d_device_SerialNumber_var(<xsl:value-of select="$nsprefix"/>_SerialNumber) =
        "<xsl:value-of select="wdp:ThisDevice/wdp:SerialNumber"/>";
    </xsl:if>
</xsl:template>

<xsl:template match="wdp:ThisDevice">
void <xsl:value-of select="$nsprefix"/>_setmetadata_ThisDevice(struct dpws_s *device)
{
  struct ws4d_thisDevice *_device = dpws_change_thisdevice(device);

  <xsl:if test="count(wdp:FriendlyName) != 0">
  ws4d_device_set_FriendlyName(_device, <xsl:value-of select="$nsprefix"/>_FriendlyName,<xsl:value-of select="count(wdp:FriendlyName)"/>);
  </xsl:if>

  <xsl:if test="count(wdp:FirmwareVersion) != 0">
  ws4d_device_set_FirmwareVersion(_device, <xsl:value-of select="$nsprefix"/>_FirmwareVersion);
  </xsl:if>

  <xsl:if test="count(wdp:SerialNumber) != 0">
  ws4d_device_set_SerialNumber(_device, <xsl:value-of select="$nsprefix"/>_SerialNumber);
  </xsl:if>
}
</xsl:template>

<xsl:template name="ModelConstantDefinition">
/* Model Metadata */

    <xsl:if test="count(wdp:ThisModel/wdp:Manufacturer) != 0">
const ws4d_model_Manufacturer_var(<xsl:value-of select="$nsprefix"/>_Manufacturer) = {
      <xsl:for-each select="wdp:ThisModel/wdp:Manufacturer">
	ws4d_locstring_init_string("<xsl:value-of select="@lang"/>", "<xsl:value-of select="text()"/>"),
      </xsl:for-each>
 };
    </xsl:if>

    <xsl:if test="count(wdp:ThisModel/wdp:ManufacturerUrl) != 0">
const ws4d_model_ManufacturerUrl_var(<xsl:value-of select="$nsprefix"/>_ManufacturerUrl) =
        "<xsl:value-of select="wdp:ThisModel/wdp:ManufacturerUrl"/>";
    </xsl:if>

    <xsl:if test="count(wdp:ThisModel/wdp:ModelName) != 0">
const ws4d_model_Name_var(<xsl:value-of select="$nsprefix"/>_Name) = {
      <xsl:for-each select="wdp:ThisModel/wdp:ModelName">
	ws4d_locstring_init_string("<xsl:value-of select="@lang"/>", "<xsl:value-of select="text()"/>"),
      </xsl:for-each>
 };
    </xsl:if>

    <xsl:if test="count(wdp:ThisModel/wdp:ModelNumber) != 0">
const ws4d_model_Number_var(<xsl:value-of select="$nsprefix"/>_Number) =
        "<xsl:value-of select="wdp:ThisModel/wdp:ModelNumber"/>";
    </xsl:if>

    <xsl:if test="count(wdp:ThisModel/wdp:ModelUrl) != 0">
const ws4d_model_Url_var(<xsl:value-of select="$nsprefix"/>_ModelUrl) =
        "<xsl:value-of select="wdp:ThisModel/wdp:ModelUrl"/>";
    </xsl:if>

    <xsl:if test="count(wdp:ThisModel/wdp:PresentationUrl) != 0">
const ws4d_model_PresentationUrl_var(<xsl:value-of select="$nsprefix"/>_PresentationUrl) =
        "<xsl:value-of select="wdp:ThisModel/wdp:PresentationUrl"/>";
    </xsl:if>
</xsl:template>

<xsl:template match="wdp:ThisModel">
void <xsl:value-of select="$nsprefix"/>_setmetadata_ThisModel(struct dpws_s *device)
{
    struct ws4d_thisModel *model = dpws_change_thismodel(device);

    <xsl:if test="count(wdp:Manufacturer) != 0">
    ws4d_model_set_Manufacturer(model, <xsl:value-of select="$nsprefix"/>_Manufacturer,<xsl:value-of select="count(wdp:Manufacturer)"/>);
    </xsl:if>

    <xsl:if test="count(wdp:ManufacturerUrl) != 0">
    ws4d_model_set_ManufacturerUrl(model, <xsl:value-of select="$nsprefix"/>_ManufacturerUrl);
    </xsl:if>

    <xsl:if test="count(wdp:ModelName) != 0">
    ws4d_model_set_Name(model, <xsl:value-of select="$nsprefix"/>_Name,<xsl:value-of select="count(wdp:ModelName)"/>);
    </xsl:if>

    <xsl:if test="count(wdp:ModelNumber) != 0">
    ws4d_model_set_Number(model, <xsl:value-of select="$nsprefix"/>_Number);
    </xsl:if>

    <xsl:if test="count(wdp:ModelUrl) != 0">
    ws4d_model_set_Url(model, <xsl:value-of select="$nsprefix"/>_ModelUrl);
    </xsl:if>

    <xsl:if test="count(wdp:PresentationUrl) != 0">
    ws4d_model_set_PresentationUrl(model, <xsl:value-of select="$nsprefix"/>_PresentationUrl);
    </xsl:if>
}
</xsl:template>

<xsl:template match="wdp:Relationship">
	<xsl:apply-templates/>
</xsl:template>

<xsl:template match="node()" mode="AddType" name="AddType">
	<xsl:param name="type" select="."/>
	<xsl:param name="begin" select="."/>
	<xsl:param name="middle" select="."/>
	<xsl:param name="end" select="."/>
	<xsl:choose>
 		<xsl:when test="contains($type, ':')">
 			<xsl:value-of select="$begin" />
			<xsl:for-each select="namespace::*">
				<xsl:choose>
 					<xsl:when test="contains(substring-before($type, ':'), name(.))">
						"<xsl:value-of select="."/>"
					</xsl:when>
				</xsl:choose>
			</xsl:for-each>
			<xsl:value-of select="$middle" />
 			"<xsl:value-of select="substring-after($type, ':')" />"
 			<xsl:value-of select="$end" />
 		</xsl:when>
	</xsl:choose>
</xsl:template>

<xsl:template match="node()" mode="AddTypes" name="AddTypes">
	<xsl:param name="types" select="."/>
	<xsl:param name="begin" select="."/>
	<xsl:param name="middle" select="."/>
	<xsl:param name="end" select="."/>
	<xsl:choose>
 		<xsl:when test="contains($types, ' ')">
			<xsl:call-template name="AddType">
        <xsl:with-param name="type"
                        select="substring-before($types, ' ')" />
				<xsl:with-param name="begin"
                        select="$begin" />
				<xsl:with-param name="middle"
                        select="$middle" />
				<xsl:with-param name="end"
                        select="$end" />
	  	</xsl:call-template>
      <xsl:call-template name="AddTypes">
        <xsl:with-param name="types"
                        select="substring-after($types, ' ')" />
				<xsl:with-param name="begin"
                        select="$begin" />
				<xsl:with-param name="middle"
                        select="$middle" />
				<xsl:with-param name="end"
                        select="$end" />
      </xsl:call-template>
 		</xsl:when>
 		<xsl:otherwise>
			<xsl:call-template name="AddType">
        <xsl:with-param name="type"
                        select="$types" />
				<xsl:with-param name="begin"
                        select="$begin" />
				<xsl:with-param name="middle"
                        select="$middle" />
				<xsl:with-param name="end"
                        select="$end" />
	  	</xsl:call-template>
  	</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template match="wdp:Host">
int <xsl:value-of select="$nsprefix"/>_setup_HostingService(struct dpws_s *device, struct soap *hosting, const char *deviceid, int backlog)
{
  struct ws4d_qname *device_type;
  int ret = 0;

  ret = dpws_add_hosting_service (device, hosting, 0,<xsl:choose><xsl:when test="count(wdp:ServiceId)!= 0">
                                  &quot;<xsl:value-of select="wdp:ServiceId"/>&quot;,</xsl:when><xsl:otherwise>
                                  NULL,</xsl:otherwise></xsl:choose><xsl:choose><xsl:when test="count(wsa:EndpointReference/wsa:Address)!= 0">
                                  &quot;<xsl:value-of select="wsa:EndpointReference/wsa:Address"/>&quot;,</xsl:when><xsl:otherwise>
                                  deviceid,</xsl:otherwise></xsl:choose>
                                  backlog);

  device_type = NULL;
  <xsl:choose>
	<xsl:when test="wdp:Types!=''">
  if (ret != WS4D_OK)
    return ret;
	<xsl:call-template name="AddTypes">
        <xsl:with-param name="types"
                        select="wdp:Types" />
				<xsl:with-param name="begin">
  device_type = ws4d_qname_alloc (1, &amp;device->alloc_list);
  device_type->ns = ws4d_strdup (</xsl:with-param><xsl:with-param name="middle">, &amp;device->alloc_list);
  device_type->name = ws4d_strdup (</xsl:with-param><xsl:with-param name="end">, &amp;device->alloc_list);
  ret = dpws_add_type (device, device_type);
  </xsl:with-param>
  </xsl:call-template>
	</xsl:when>
	</xsl:choose>
  return ret;
}
</xsl:template>

<xsl:template match="wdp:Hosted">

int <xsl:value-of select="$nsprefix"/>_setup_<xsl:value-of select="Name"/>(struct dpws_s *device, struct soap *handle, const char *wsdl, int backlog)
{
  char uri[DPWS_URI_MAX_LEN] = <xsl:choose><xsl:when test="count(wsa:EndpointReference/wsa:Address)!= 0">&quot;<xsl:value-of select="wsa:EndpointReference/wsa:Address"/>&quot;;</xsl:when><xsl:otherwise>"http://host:0/";</xsl:otherwise></xsl:choose>
  struct ws4d_qname *service_type;
  struct ws4d_epr *service = NULL;

  service = dpws_service_init(device, &quot;<xsl:value-of select="wdp:ServiceId"/>&quot;);

  if (dpws_service_bind (device, service, handle, uri, DPWS_URI_MAX_LEN, backlog))
    {
      return WS4D_ERR;
    }

  service_type = NULL;
  <xsl:choose>
	<xsl:when test="wdp:Types!=''">
	<xsl:call-template name="AddTypes">
        <xsl:with-param name="types"
                        select="wdp:Types" />
				<xsl:with-param name="begin">
  service_type = ws4d_qname_alloc (1, &amp;device->alloc_list);
  service_type->ns = ws4d_strdup (</xsl:with-param><xsl:with-param name="middle">, &amp;device->alloc_list);
  service_type->name = ws4d_strdup (</xsl:with-param><xsl:with-param name="end">, &amp;device->alloc_list);
  dpws_service_add_type (service, service_type);
  </xsl:with-param>
  </xsl:call-template>
	</xsl:when>
	</xsl:choose>

  if (dpws_service_set_wsdl(service, wsdl))
    return WS4D_ERR;

  return dpws_add_hosted_service (device, service, uri, DPWS_URI_MAX_LEN);
}
</xsl:template></xsl:stylesheet>