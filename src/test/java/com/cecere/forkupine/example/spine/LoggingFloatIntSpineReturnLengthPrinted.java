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
package com.cecere.forkupine.example.spine;

import com.cecere.forkupine.data.Data;
import com.cecere.forkupine.data.None;
import com.cecere.forkupine.data.NoneImpl;
import com.cecere.forkupine.data.Some;
import com.cecere.forkupine.data.SomeImpl;
import com.cecere.forkupine.data.process.DataFlowProcessor2;
import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.Spine2;

/**
 * @author dave
 *
 */
public class LoggingFloatIntSpineReturnLengthPrinted implements DataFlowProcessor2<Some<Float>,Some<Integer>,Some<Integer>>{

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine2#process(com.cecere.forkupine.data.Data, com.cecere.forkupine.data.Data)
	 */
	@Override
	public Some<Integer> process(Some<Float> input1, Some<Integer> input2) {
		String s = "float: "+input1.get()+" - integer: "+input2.get();
		System.out.println(s);
		return new SomeImpl<Integer>(s.length());
	}
}
