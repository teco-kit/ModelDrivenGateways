
#ifndef CONVERSION_H_
#define CONVERSION_H_

/**
 * Write SOAP output
 */
void writeSOAPBuf(struct soap *soap,char * buf);

/**
 * Write acceleration values to SOAP output
 */
void writeSOAPValues(struct soap *soap,float x,float y,float z);

#endif /* CONVERSION_H_ */
