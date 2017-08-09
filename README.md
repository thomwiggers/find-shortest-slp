Find the shortest SLP by transforming it to SAT
===============================================

Implements Fuhs and Schneider-Kamp's paper "Synthesizing Shortest Linear
Straight-Line Programs over GF(2) using SAT". 

Part of my Bachelor Thesis project. See https://thomwiggers.nl/proest/

Usage
-----

### Build dependencies

* Maven
* Java development environment

### Getting started

1. Compile with `mvn package`
2. See `run.sh` for a convenient wrapper with some jvm options.

### Example

    ./run.sh 6 < test     # Try input file test for k = 5
    ./run.sh 10 5 < test  # Try it for 10 <= k <= 5

LICENCE
-------
Copyright (C) 2015  Thom Wiggers

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
