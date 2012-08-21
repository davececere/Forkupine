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
package com.cecere.forkupine.process.parameter;

import java.util.ArrayList;
import java.util.List;

import com.cecere.forkupine.data.Data;
import com.cecere.forkupine.data.Some;
import com.cecere.forkupine.data.SomeImpl;
import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.forkjoin.RecursiveSpineImpl;

/**
 * @author dave
 *
 */
public class IdentitySpine<I> implements Spine<Some<I>, Some<I>> {

	private I data;
	protected List<Spine<Some<I>,? extends Data>> nextNodes;
	
	public IdentitySpine(I input){
		data = input;
		nextNodes = new ArrayList<Spine<Some<I>,? extends Data>>();
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#process(com.cecere.forkupine.data.Data)
	 */
	@Override
	public Some<I> process(Some<I> input) {
		return new SomeImpl<I>(data);
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#flowsInto(com.cecere.forkupine.process.Spine)
	 */
	@Override
	public Spine<Some<I>, Some<I>> flowsInto(Spine<Some<I>, ? extends Data> child) {
		nextNodes.add(child);
		child.flowsFrom(this);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#flowsFrom(com.cecere.forkupine.process.Spine)
	 */
	@Override
	public void flowsFrom(Spine<? extends Data, Some<I>> parent) {
		throw new RuntimeException("unsupported operation"); //identity spines have no input but via constructor
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#getPrevNode()
	 */
	@Override
	public Spine<? extends Data, Some<I>> getPrevNode() {
		//no parents
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#toRecursiveSpine()
	 */
	@Override
	public RecursiveSpineImpl<Some<I>, Some<I>> toRecursiveSpine() {
		// TODO Auto-generated method stub
		return new RecursiveSpineImpl(this);
	}

}
