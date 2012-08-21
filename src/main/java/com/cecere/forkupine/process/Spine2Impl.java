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
import com.cecere.forkupine.process.output.Spine2Output;
import com.cecere.forkupine.process.parameter.Spine2ParameterA;
import com.cecere.forkupine.process.parameter.Spine2ParameterB;

abstract public class Spine2Impl<A extends Data,B extends Data,O extends Data> implements Spine2<A,B,O> {
	protected List<Spine<O,? extends Data>> nextNodes;
	protected Spine<? extends Data,A> parentA;
	protected Spine<? extends Data,B> parentB;
	protected Spine2ParameterA<A,O> param1;
	protected Spine2ParameterB<B,O> param2;
	
	public Spine2Impl(){
		nextNodes = new ArrayList<Spine<O,? extends Data>>();
	}
	
	public Spine2<A, B, O> flowsInto(Spine<O, ? extends Data> child) {
		nextNodes.add(child);
		child.flowsFrom(new Spine2Output<A,B,O>(this));
		return this;
	}
	
	@Override
	public void flowsFromA(Spine<? extends Data,A> parent){
		parentA = parent;
	}
	
	@Override
	public void flowsFromB(Spine<? extends Data,B> parent){
		parentB = parent;
	}

	public Spine<A,?> asParam1(){
		param1 = new Spine2ParameterA<A,O>(this);
		return param1;
	}
	public Spine<B,?> asParam2(){
		param2 = new Spine2ParameterB<B,O>(this);
		return param2;
	}
	
	abstract public O process(A input1,B input2);
}
