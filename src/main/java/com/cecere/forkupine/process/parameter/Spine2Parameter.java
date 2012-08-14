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

import com.cecere.forkupine.data.Data;
import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.Spine2;

/**
 * @author dave
 *
 */
public class Spine2Parameter<I extends Data> implements Spine<I, I> {
	private I data; //store the data
	/**
	 * @return the data
	 */
	public I getData() {
		return data;
	}

	private Spine2<? extends Data,? extends Data,? extends Data> delegate;

	public Spine2Parameter(Spine2<? extends Data,? extends Data,? extends Data> delegate){
		this.delegate = delegate;
	}
	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#process(com.cecere.forkupine.data.Data)
	 */
	public void process(I input) {
		data = input;
		delegate.process(null, null);
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#flowsInto(com.cecere.forkupine.process.Spine)
	 */
	public <V extends Data> Spine<I, I> flowsInto(Spine<I, V> child) {
		return this;
	}

}
