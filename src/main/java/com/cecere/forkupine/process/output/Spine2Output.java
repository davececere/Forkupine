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
package com.cecere.forkupine.process.output;

import com.cecere.forkupine.data.Data;
import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.Spine2;
import com.cecere.forkupine.process.forkjoin.RecursiveSpineImpl;

/**
 * @author dave
 *
 */
public class Spine2Output<A extends Data,B extends Data, O extends Data> implements Spine<A, O> {

	protected Spine2<? extends Data, ? extends Data, O> delegate;
	
	public Spine2Output(Spine2<? extends Data, ? extends Data, O> delegate){
		this.delegate = delegate;
	}
	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#process(com.cecere.forkupine.data.Data)
	 */
	@Override
	public O process(A input) {
		throw new RuntimeException("not yet implemented"); //will we need this?
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#flowsInto(com.cecere.forkupine.process.Spine)
	 */
	@Override
	public Spine<A, O> flowsInto(Spine<O, ? extends Data> child) {
		throw new RuntimeException("not yet implemented"); //will we need this?
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#flowsFrom(com.cecere.forkupine.process.Spine)
	 */
	@Override
	public void flowsFrom(Spine<? extends Data, A> parent) {
		throw new RuntimeException("not yet implemented");
	}
	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#getPrevNode()
	 */
	@Override
	public Spine<? extends Data, A> getPrevNode() {
		return null;
	}
	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#toRecursiveSpine()
	 */
	@Override
	public RecursiveSpineImpl<A, O> toRecursiveSpine() {
		// TODO Auto-generated method stub
		return null;
	}

}
