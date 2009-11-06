# Copyright (C) 2007  University of Rostock
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License as
# published by the Free Software Foundation; either version 2 of the
# License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful, but
# WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
# 02110-1301 USA.

IF(MSVC)
	INSTALL(FILES cygiconv-2.dll cygintl-3.dll cygwin1.dll iconv.dll libexslt.dll libxml2.dll libxslt.dll patch.exe sed.exe xsltproc.exe zlib1.dll DESTINATION bin)
ENDIF(MSVC)
