#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <limits.h>
#include <sys/types.h>
#include <stdint.h>

#include "../bitsio/bits_io.h"
#include "write_str_func.h"
#include "decoder_automata.h"

typedef struct {
	int idx;
	int elemCount;
} IdxMemo;
static int elemCount = 0;
static int idx = 1;
static IdxMemo *stack = NULL;
#define STD_STACK_SIZE 5
static int curr_size = STD_STACK_SIZE;
static int curr_idx = -1;
static int fd = -1;

void automata_init(int _fd) {
	stack = (IdxMemo *) malloc(curr_size * sizeof(IdxMemo));
	(void) memset(stack, 0, curr_size * sizeof(IdxMemo));
	fd = _fd;
	write_init(fd);
}

void automata_finish(void) {
	write_finish();

	if (stack != NULL) {
		free(stack);
		stack = NULL;
	}
}

int getElementDecoder(char * xmlElement,
		struct STR_WRITER_STRUCT * writer_struct) {
	while (1) {
		switch (idx) {

		case 1: /* SensorValues */
			if (strcmp("SensorValues", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"SensorValues upB 1"
						" lowB 1");
				elemCount = 0;

			} else {
				elemCount++;
				return 0;

			}
		case 2: /* AcclX */
			if (strcmp("AcclX", xmlElement) != 0) {
				idx++;
				if (strcmp("AcclValue", xmlElement) == 0) {
					if (elemCount > 1)
						fprintf(stderr, "Error. Wrong count of element "
							"AcclX upB 1"
							" lowB 0");
					if (++curr_idx >= curr_size) {
						curr_size += STD_STACK_SIZE;
						stack = realloc(stack, (curr_size) * sizeof(IdxMemo));
					}
					stack[curr_idx].idx = 2;
					stack[curr_idx].elemCount = elemCount;
					elemCount = 0;
					continue;
				} else {
					idx = 5;
					write_false();
					elemCount = 0;
					continue;
				}

				write_false();

				elemCount = 0;

			} else {
				elemCount++;
				write_true();
				return 0;

			}
		case 3: /* AcclValue */
			if (strcmp("AcclValue", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"AcclValue upB 1"
						" lowB 1");
				elemCount = 0;

			} else {
				elemCount++;

				writer_struct->func = str_write_short;

				return 1;

			}
		case 4: /* AcclIdx */
			if (strcmp("AcclIdx", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"AcclIdx upB 1"
						" lowB 1");
				elemCount = 0;
				if (curr_idx > -1) {
					idx = stack[curr_idx].idx;
					elemCount = stack[curr_idx].elemCount;
					curr_idx--;
					continue;
				}

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 5: /* AcclY */
			if (strcmp("AcclY", xmlElement) != 0) {
				idx++;
				if (strcmp("AcclValue", xmlElement) == 0) {
					if (elemCount > 1)
						fprintf(stderr, "Error. Wrong count of element "
							"AcclY upB 1"
							" lowB 0");
					if (++curr_idx >= curr_size) {
						curr_size += STD_STACK_SIZE;
						stack = realloc(stack, (curr_size) * sizeof(IdxMemo));
					}
					stack[curr_idx].idx = 5;
					stack[curr_idx].elemCount = elemCount;
					elemCount = 0;
					continue;
				} else {
					idx = 8;
					write_false();
					elemCount = 0;
					continue;
				}

				write_false();

				elemCount = 0;

			} else {
				elemCount++;
				write_true();
				return 0;

			}
		case 6: /* AcclValue */
			if (strcmp("AcclValue", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"AcclValue upB 1"
						" lowB 1");
				elemCount = 0;

			} else {
				elemCount++;

				writer_struct->func = str_write_short;

				return 1;

			}
		case 7: /* AcclIdx */
			if (strcmp("AcclIdx", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"AcclIdx upB 1"
						" lowB 1");
				elemCount = 0;
				if (curr_idx > -1) {
					idx = stack[curr_idx].idx;
					elemCount = stack[curr_idx].elemCount;
					curr_idx--;
					continue;
				}

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 8: /* AcclZ */
			if (strcmp("AcclZ", xmlElement) != 0) {
				idx++;
				if (strcmp("AcclValue", xmlElement) == 0) {
					if (elemCount > 1)
						fprintf(stderr, "Error. Wrong count of element "
							"AcclZ upB 1"
							" lowB 0");
					if (++curr_idx >= curr_size) {
						curr_size += STD_STACK_SIZE;
						stack = realloc(stack, (curr_size) * sizeof(IdxMemo));
					}
					stack[curr_idx].idx = 8;
					stack[curr_idx].elemCount = elemCount;
					elemCount = 0;
					continue;
				} else {
					idx = 11;
					write_false();
					elemCount = 0;
					continue;
				}

				write_false();

				elemCount = 0;

			} else {
				elemCount++;
				write_true();
				return 0;

			}
		case 9: /* AcclValue */
			if (strcmp("AcclValue", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"AcclValue upB 1"
						" lowB 1");
				elemCount = 0;

			} else {
				elemCount++;

				writer_struct->func = str_write_short;

				return 1;

			}
		case 10: /* AcclIdx */
			if (strcmp("AcclIdx", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"AcclIdx upB 1"
						" lowB 1");
				elemCount = 0;
				if (curr_idx > -1) {
					idx = stack[curr_idx].idx;
					elemCount = stack[curr_idx].elemCount;
					curr_idx--;
					continue;
				}

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 11: /* Audio */
			if (strcmp("Audio", xmlElement) != 0) {
				idx++;

				write_false();

				elemCount = 0;

			} else {
				elemCount++;
				write_true();

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 12: /* Light */
			if (strcmp("Light", xmlElement) != 0) {
				idx++;
				if (strcmp("LightValue", xmlElement) == 0) {
					if (elemCount > 1)
						fprintf(stderr, "Error. Wrong count of element "
							"Light upB 1"
							" lowB 0");
					if (++curr_idx >= curr_size) {
						curr_size += STD_STACK_SIZE;
						stack = realloc(stack, (curr_size) * sizeof(IdxMemo));
					}
					stack[curr_idx].idx = 12;
					stack[curr_idx].elemCount = elemCount;
					elemCount = 0;
					continue;
				} else {
					idx = 15;
					write_false();
					elemCount = 0;
					continue;
				}

				write_false();

				elemCount = 0;

			} else {
				elemCount++;
				write_true();
				return 0;

			}
		case 13: /* LightValue */
			if (strcmp("LightValue", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"LightValue upB 1"
						" lowB 1");
				elemCount = 0;

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 14: /* LightIdx */
			if (strcmp("LightIdx", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"LightIdx upB 1"
						" lowB 1");
				elemCount = 0;
				if (curr_idx > -1) {
					idx = stack[curr_idx].idx;
					elemCount = stack[curr_idx].elemCount;
					curr_idx--;
					continue;
				}

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 15: /* AmbientLight */
			if (strcmp("AmbientLight", xmlElement) != 0) {
				idx++;
				if (strcmp("LightValue", xmlElement) == 0) {
					if (elemCount > 1)
						fprintf(stderr, "Error. Wrong count of element "
							"AmbientLight upB 1"
							" lowB 0");
					if (++curr_idx >= curr_size) {
						curr_size += STD_STACK_SIZE;
						stack = realloc(stack, (curr_size) * sizeof(IdxMemo));
					}
					stack[curr_idx].idx = 15;
					stack[curr_idx].elemCount = elemCount;
					elemCount = 0;
					continue;
				} else {
					idx = 18;
					write_false();
					elemCount = 0;
					continue;
				}

				write_false();

				elemCount = 0;

			} else {
				elemCount++;
				write_true();
				return 0;

			}
		case 16: /* LightValue */
			if (strcmp("LightValue", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"LightValue upB 1"
						" lowB 1");
				elemCount = 0;

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 17: /* LightIdx */
			if (strcmp("LightIdx", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"LightIdx upB 1"
						" lowB 1");
				elemCount = 0;
				if (curr_idx > -1) {
					idx = stack[curr_idx].idx;
					elemCount = stack[curr_idx].elemCount;
					curr_idx--;
					continue;
				}

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 18: /* Force */
			if (strcmp("Force", xmlElement) != 0) {
				idx++;
				if (strcmp("ForceValue", xmlElement) == 0) {
					if (elemCount > 1)
						fprintf(stderr, "Error. Wrong count of element "
							"Force upB 1"
							" lowB 0");
					if (++curr_idx >= curr_size) {
						curr_size += STD_STACK_SIZE;
						stack = realloc(stack, (curr_size) * sizeof(IdxMemo));
					}
					stack[curr_idx].idx = 18;
					stack[curr_idx].elemCount = elemCount;
					elemCount = 0;
					continue;
				} else {
					idx = 21;
					write_false();
					elemCount = 0;
					continue;
				}

				write_false();

				elemCount = 0;

			} else {
				elemCount++;
				write_true();
				return 0;

			}
		case 19: /* ForceValue */
			if (strcmp("ForceValue", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"ForceValue upB 1"
						" lowB 1");
				elemCount = 0;

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 20: /* ForceIdx */
			if (strcmp("ForceIdx", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"ForceIdx upB 1"
						" lowB 1");
				elemCount = 0;
				if (curr_idx > -1) {
					idx = stack[curr_idx].idx;
					elemCount = stack[curr_idx].elemCount;
					curr_idx--;
					continue;
				}

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 21: /* Temperature */
			if (strcmp("Temperature", xmlElement) != 0) {
				idx++;
				if (strcmp("TempValue", xmlElement) == 0) {
					if (elemCount > 1)
						fprintf(stderr, "Error. Wrong count of element "
							"Temperature upB 1"
							" lowB 0");
					if (++curr_idx >= curr_size) {
						curr_size += STD_STACK_SIZE;
						stack = realloc(stack, (curr_size) * sizeof(IdxMemo));
					}
					stack[curr_idx].idx = 21;
					stack[curr_idx].elemCount = elemCount;
					elemCount = 0;
					continue;
				} else {
					idx = 24;
					write_false();
					elemCount = 0;
					continue;
				}

				write_false();

				elemCount = 0;

			} else {
				elemCount++;
				write_true();
				return 0;

			}
		case 22: /* TempValue */
			if (strcmp("TempValue", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"TempValue upB 1"
						" lowB 1");
				elemCount = 0;

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 23: /* TempIdx */
			if (strcmp("TempIdx", xmlElement) != 0) {
				idx++;

				if (elemCount > 1 || elemCount < 1)
					fprintf(stderr, "Error. Wrong count of element "
						"TempIdx upB 1"
						" lowB 1");
				elemCount = 0;
				if (curr_idx > -1) {
					idx = stack[curr_idx].idx;
					elemCount = stack[curr_idx].elemCount;
					curr_idx--;
					continue;
				}

			} else {
				elemCount++;

				writer_struct->func = str_write_byte;

				return 1;

			}
		case 24: /* Voltage */
			if (strcmp("Voltage", xmlElement) != 0) {
				idx++;

				write_false();

				elemCount = 0;
				if (curr_idx > -1) {
					idx = stack[curr_idx].idx;
					elemCount = stack[curr_idx].elemCount;
					curr_idx--;
					continue;
				}

			} else {
				elemCount++;
				write_true();

				writer_struct->func = str_write_short;

				return 1;

			}

		default:
			if (idx < 26)
				idx++;
			else
				idx = 1;
			elemCount = 0;
			fprintf(stderr, "Unknown id");
		}
	}
}

