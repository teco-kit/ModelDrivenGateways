/*
 * Copyright (c) 2007
 * Telecooperation Office (TecO), Universitaet Karlsruhe (TH), Germany.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 * 3. Neither the name of the Universitaet Karlsruhe (TH) nor the names
 *    of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written
 *    permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Author(s): Philipp Scholl <scholl@teco.edu>
 */

/** autogenerated by aclgen.py
 *
 * Contains the char-table for concom types. Converting a "string" to a concom
 * type works by calling acltype(a,b,c) which returns the concom type of "abc".
 *
 */

#ifndef  _ACL_TYPES_H_
# define _ACL_TYPES_H_

# define aclchr(x) ACLCHR_##x
# define acltype(a,b,c) ((uint16_t) aclchr(c)*1600 + (uint16_t) aclchr(b)*40 + (uint16_t) aclchr(a))

# define ACLCHR_0 0
# define ACLCHR_1 1
# define ACLCHR_2 2
# define ACLCHR_3 3
# define ACLCHR_4 4
# define ACLCHR_5 5
# define ACLCHR_6 6
# define ACLCHR_7 7
# define ACLCHR_8 8
# define ACLCHR_9 9
# define ACLCHR__ 12
# define ACLCHR_  12
# define ACLCHR_A 14
# define ACLCHR_B 15
# define ACLCHR_C 16
# define ACLCHR_D 17
# define ACLCHR_E 18
# define ACLCHR_F 19
# define ACLCHR_G 20
# define ACLCHR_H 21
# define ACLCHR_I 22
# define ACLCHR_J 23
# define ACLCHR_K 24
# define ACLCHR_L 25
# define ACLCHR_M 26
# define ACLCHR_N 27
# define ACLCHR_O 28
# define ACLCHR_P 29
# define ACLCHR_Q 30
# define ACLCHR_R 31
# define ACLCHR_S 32
# define ACLCHR_T 33
# define ACLCHR_U 34
# define ACLCHR_V 35
# define ACLCHR_W 36
# define ACLCHR_X 37
# define ACLCHR_Y 38
# define ACLCHR_Z 39
# define ACLCHR_a 14
# define ACLCHR_b 15
# define ACLCHR_c 16
# define ACLCHR_d 17
# define ACLCHR_e 18
# define ACLCHR_f 19
# define ACLCHR_g 20
# define ACLCHR_h 21
# define ACLCHR_i 22
# define ACLCHR_j 23
# define ACLCHR_k 24
# define ACLCHR_l 25
# define ACLCHR_m 26
# define ACLCHR_n 27
# define ACLCHR_o 28
# define ACLCHR_p 29
# define ACLCHR_q 30
# define ACLCHR_r 31
# define ACLCHR_s 32
# define ACLCHR_t 33
# define ACLCHR_u 34
# define ACLCHR_v 35
# define ACLCHR_w 36
# define ACLCHR_x 37
# define ACLCHR_y 38
# define ACLCHR_z 39

#endif
