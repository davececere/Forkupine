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
import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.Spine2;

/**
 * @author dave
 *
 */
public class LoggingSpine implements Spine2<Some<Float>,Some<Integer>,None>{

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine2#process(com.cecere.forkupine.data.Data, com.cecere.forkupine.data.Data)
	 */
	public None process(Some<Float> input1, Some<Integer> input2) {
		System.out.println("float: "+input1.get()+" - integer: "+input2.get());
		return new NoneImpl();
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine2#flowsInto(com.cecere.forkupine.process.Spine)
	 */
	public <V extends Data> Spine2<Some<Float>, Some<Integer>, None> flowsInto(
			Spine<None, V> child) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine2#asParam1()
	 */
	public Spine<Some<Float>, ?> asParam1() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine2#asParam2()
	 */
	public Spine<Some<Integer>, ?> asParam2() {
		// TODO Auto-generated method stub
		return null;
	}

}
