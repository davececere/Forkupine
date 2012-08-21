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
package com.cecere.forkupine.process.forkjoin;

import jsr166y.RecursiveAction;
import jsr166y.RecursiveTask;

import com.cecere.forkupine.data.Data;
import com.cecere.forkupine.process.Spine;

/**
 * @author dave
 *
 */
public class RecursiveSpineImpl<I extends Data,O extends Data> extends RecursiveTask<O>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7947360043351343429L;
	Spine<I,O> delegate;
	
	public RecursiveSpineImpl(Spine<I,O> spine){
		delegate = spine;
	}

	/* (non-Javadoc)
	 * @see jsr166y.RecursiveAction#compute()
	 */
	@Override
	protected O compute() {
		//TODO: really crappy. we assume the implementation of a delegate that returns null ignores input. refactor
		if(delegate.getPrevNode() != null) {
			//can we get away with this non-generic constructor?
			RecursiveTask<I> prevTask = delegate.getPrevNode().toRecursiveSpine();
			prevTask.fork();
			I input = prevTask.join();
			O output = delegate.process(input);
			return output;
		}else { 
			O output = delegate.process(null);
			return output;
		}
	}



}
