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

import java.util.ArrayList;
import java.util.List;

import com.cecere.forkupine.data.Data;
import com.cecere.forkupine.data.Some;
import com.cecere.forkupine.process.forkjoin.RecursiveSpineImpl;

abstract public class SpineImpl<I extends Data,O extends Data> implements Spine<I,O> {
	protected List<Spine<O,? extends Data>> nextNodes;
	protected Spine<? extends Data,I> prevNode;

	public SpineImpl(){
		nextNodes = new ArrayList<Spine<O,? extends Data>>();
	}

	@Override
	public Spine<I, O> flowsInto(Spine<O, ? extends Data> child) {
		nextNodes.add(child);
		child.flowsFrom(this);
		return this;
	}
	
	@Override
	public void flowsFrom(Spine<? extends Data,I> parent){
		prevNode = parent;
	}

	
	/**
	 * @return the prevNode
	 */
	public Spine<? extends Data, I> getPrevNode() {
		return prevNode;
	}
	
	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#toRecursiveSpine()
	 */
	@Override
	public RecursiveSpineImpl<I, O> toRecursiveSpine() {
		// TODO Auto-generated method stub
		return new RecursiveSpineImpl<I,O>(this);
	}
	
	abstract public O process(I input);
}
