using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml.Linq;
using System.Xml;
using System.Xml.Serialization;
using System.Text;
using System.ServiceModel;
using System.ServiceModel.Discovery;
using NodeDiscovery.WSEventing;
using NodeDiscovery.www.teco.edu;


namespace NodeDiscovery
{
    class Program : SensorValuesCallback,EventSourceCallback
    {
          public void SensorValuesEvent(SensorValuesEvent request)
   {
       throw new NotImplementedException();
   }
    public void SubscriptionEnd(SubscriptionEnd1 request)
    {
              throw new NotImplementedException();
    }

        public static void Main()
        {
            EndpointAddress endpointAddress = FindServiceAddress();

            if (endpointAddress != null)
            {
                new Program().InvokeService(endpointAddress);
            }

            Console.WriteLine("Press <ENTER> to exit.");
            Console.ReadLine();

        }

        static EndpointAddress FindServiceAddress()
        {
            // Create DiscoveryClient
            DiscoveryClient discoveryClient = new DiscoveryClient(new UdpDiscoveryEndpoint());

    //        discoveryClient.Endpoint.Binding.ReceiveTimeout = new TimeSpan(0,0,1);

            Console.WriteLine("Finding endpoints via UDP Discovery");
            // Find ICalculatorService endpoints            
            FindResponse findResponseX = discoveryClient.Find(new FindCriteria());

            Console.WriteLine("Found {0} ServiceHost endpoint(s).", findResponseX.Endpoints.Count);
            Console.WriteLine();

            if (findResponseX.Endpoints.Count > 0)
            {
                foreach (EndpointDiscoveryMetadata meta in findResponseX.Endpoints)
                {

                    foreach (System.Xml.XmlQualifiedName type in meta.ContractTypeNames)
                    {
                        Console.WriteLine("Checking {0}@{1}", type, meta.Address);
                        if (type.Equals(new System.Xml.XmlQualifiedName("SSimpDeviceType", "http://www.teco.edu/SensorValues")))
                        {
                            Console.WriteLine("Found {0}@{1}", type, meta.Address);
                            ResolveResponse resolveResponse = discoveryClient.Resolve(new ResolveCriteria(meta.Address));
                            Console.WriteLine("Resolved {0} to {1}", resolveResponse.EndpointDiscoveryMetadata.Address, resolveResponse.EndpointDiscoveryMetadata.ListenUris[0]);
                            return new EndpointAddress(resolveResponse.EndpointDiscoveryMetadata.ListenUris[0]);
                        }
                    }
                }
                return null;
            }
            else
            {
                return null;
            }
        }

        void InvokeService(EndpointAddress endpointAddress)
        {
            InstanceContext ithis=new InstanceContext(this);
            String callbackEndpoint="http://vs2010test/blub" ;

            if (true)
            {
            // Create a client
                SensorValuesClient client = new SensorValuesClient(new InstanceContext(this));
                client.Endpoint.Address = endpointAddress;
                callbackEndpoint=client.InnerChannel.LocalAddress.Uri.AbsoluteUri;

           
                Console.WriteLine("Invoking at {0}", endpointAddress);

                // client.GetSensorValues(out accelleration,out audio,out light,out force,out temperature);
                SSimpSample response = client.GetSensorValues();

                Console.WriteLine("Got response: {0}", response);
            }
            
            Console.WriteLine("Subscribing event {1} at {0}", endpointAddress,"http://www.teco.edu/SensorValues/SensorValuesEventOut");
            EventSourceClient eventSource = new EventSourceClient(ithis);
            eventSource.Endpoint.Address = endpointAddress;

            SubscribeOpRequest s=new SubscribeOpRequest();

            s.Subscribe = new Subscribe();

            (s.Subscribe.Delivery = new DeliveryType()).Mode = "http://schemas.xmlsoap.org/ws/2004/08/eventing/DeliveryModes/Push";
            
            XmlDocument doc=  new XmlDocument();
                    
           using (XmlWriter writer = doc.CreateNavigator().AppendChild())       
           {
               EndpointReferenceType notifyTo = new EndpointReferenceType();

               (notifyTo.Address = new AttributedURI()).Value = callbackEndpoint;
               XmlRootAttribute notifyToElem = new XmlRootAttribute("NotifyTo");
               notifyToElem.Namespace="http://schemas.xmlsoap.org/ws/2004/08/eventing";

               new XmlSerializer(notifyTo.GetType(),notifyToElem).Serialize(writer, notifyTo);  
           }
           
            (s.Subscribe.Delivery.Any = new XmlElement[1])[0]=
            doc.DocumentElement;
           
            (s.Subscribe.Filter=new FilterType()).Dialect="http://schemas.xmlsoap.org/ws/2006/02/devprof/Action";

            (s.Subscribe.Filter.Any=new System.Xml.XmlNode[1]) [0] =
                new System.Xml.XmlDocument().CreateTextNode("http://www.teco.edu/SensorValues/SensorValuesEventOut");
            
            SubscribeOpResponse subscription;
            try
            {
                subscription = eventSource.SubscribeOp(s);
            }
            catch (TimeoutException t )
            {
                Console.WriteLine("Error reply time out: {0}!!",t.Message);
                return;
            }
            //  eventSource.Close();

            
            String subscriptionId=null;
           
          
            foreach( XmlNode xel in subscription.SubscribeResponse.SubscriptionManager.ReferenceParameters.Any )
            {
                if(xel.LocalName.Equals("Identifier")&&xel.NamespaceURI.Equals("http://schemas.xmlsoap.org/ws/2004/08/eventing"))
                    subscriptionId = xel.InnerText;
            }

            Console.WriteLine("Got subscription: {0}", subscriptionId);

            Console.WriteLine("Press <ENTER> to unsubscribe.");
            Console.ReadLine();

            UnsubscribeOpRequest unsubscribe = new UnsubscribeOpRequest();
            unsubscribe.Identifier = subscriptionId;

            Console.WriteLine("Unsubscribing {0}", subscriptionId);
            SubscriptionManagerClient subscriptionManager = new SubscriptionManagerClient();
            subscriptionManager.Endpoint.Address = endpointAddress;
            subscriptionManager.UnsubscribeOp(unsubscribe);
            
            //Closing the client gracefully closes the connection and cleans up resources
            //client.Close();
        }






    }
}
