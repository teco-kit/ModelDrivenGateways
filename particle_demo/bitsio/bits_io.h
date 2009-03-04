/* ========================================================================
 *        Filename   : bits_io.h
 *        Description:
 *        Version    : 1.0
 *        (C)        : Dimitar Yordanov (domidimi [at] gmail [dot] com)
 *        ---- History ---------------------------------------------------
 *        03.08.2008 11:34    dy    initial version
 * ========================================================================
 */

#ifndef _BITS_IO_H_
#define _BITS_IO_H_

#ifndef u_char
#define u_char unsigned char
#endif

#ifndef ssize_t
#define ssize_t int
#endif

#ifndef size_t
#define size_t unsigned int
#endif

/* #ifndef int8_t        */
/* #define int8_t char   */
/* #endif                */

/* #ifndef int16_t       */
/* #define int16_t short */
/* #endif                */

/* #ifndef int32_t       */
/* #define int32_t long  */
/* #endif                */

#if 0
u_char LOW_N_BITS(u_char a_byte, int n);
u_char UP_N_BITS(u_char a_byte, int n);
u_char LOW_N_BITS_SHIFT(u_char a_byte, int n);
u_char UP_N_BITS_SHIFT(u_char a_byte, int n);
#endif

#define LOW_N_BITS(ab_byte , n) (ab_byte & ((1 << (n)) - 1))
#define UP_N_BITS(ab_byte , n) (ab_byte & ~((1 << (8 - n)) - 1))
#define LOW_N_BITS_SHIFT(ab_byte , n) ((ab_byte & ((1 << (n)) - 1)) << (8 - n))
#define UP_N_BITS_SHIFT(ab_byte , n) (ab_byte & ~((1 << (8 - n)) - 1)) >> (8 - n)

#endif

