/*
    Copyright 2012 David Cecere (davedw@gmail.com)

    This file is part of Forkupine.

    Forkupine is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Forkupine is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Forkupine.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cecere.forkupine.process;

import com.cecere.forkupine.data.Data;
import com.cecere.forkupine.data.Some;

abstract public class SpineImpl<I extends Data,O extends Data> implements Spine<I,O> {

	public <V extends Data> Spine<I, O> flowsInto(Spine<O, V> child) {
		return this;
	}

	abstract public Some<Integer> process(Some<String> input);
}
