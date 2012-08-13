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
package com.cecere.forkupine.process.execute;

import com.cecere.forkupine.data.Data;
import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.Spine2;
import com.cecere.forkupine.process.Spine2Impl;
import com.cecere.forkupine.process.SpineImpl;

/**
 * @author dave
 *
 */
public class DepthFirstSerialExecutingSpine2<A extends Data,B extends Data,O extends Data> extends Spine2Impl<A,B,O> {
	private Spine2<A,B,O> delegate;
	
	public DepthFirstSerialExecutingSpine2(Spine2<A,B,O> delegate){
		super();
		this.delegate = delegate;
	}
	
	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#process(com.cecere.forkupine.data.Data)
	 */
	public O process(A input1,B input2) {
		//ignore inputs and use parameters
		A cachedInput1 = this.param1.getData();
		B cachedInput2 = this.param2.getData();
		//we get called every time a parameter is updated so this will fail until the last param is fulfilled
		if(cachedInput1 == null || cachedInput2 == null)
			return null; //TODO: can we return null?
		
		O output = delegate.process(cachedInput1,cachedInput2);
		for(Spine<O, ? extends Data> s: this.nextNodes){
			s.process(output);
		}
		return output; //TODO: do we need to return O? maybe delegate and executors are different interfaces
	}

}
