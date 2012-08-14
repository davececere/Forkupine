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
import com.cecere.forkupine.process.parameter.Spine2Parameter;

abstract public class Spine2Impl<A extends Data,B extends Data,O extends Data> implements Spine2<A,B,O> {
	protected List<Spine<O,? extends Data>> nextNodes;
	protected Spine2Parameter<A> param1;
	protected Spine2Parameter<B> param2;
	
	public Spine2Impl(){
		nextNodes = new ArrayList<Spine<O,? extends Data>>();
	}
	
	public <V extends Data> Spine2<A, B, O> flowsInto(Spine<O, V> child) {
		nextNodes.add(child);
		return this;
	}

	public Spine<A,?> asParam1(){
		param1 = new Spine2Parameter<A>(this);
		return param1;
	}
	public Spine<B,?> asParam2(){
		param2 = new Spine2Parameter<B>(this);
		return param2;
	}
	
	abstract public void process(A input1,B input2);
}
