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
import com.cecere.forkupine.data.Some;
import com.cecere.forkupine.data.SomeImpl;
import com.cecere.forkupine.data.process.DataFlowProcessor;
import com.cecere.forkupine.process.Spine;

/**
 * @author dave
 *
 */
public class Add100Spine implements DataFlowProcessor<Some<Integer>, Some<Integer>> {

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.Spine#process(com.cecere.forkupine.data.Data)
	 */
	@Override
	public Some<Integer> process(Some<Integer> input) {
		return new SomeImpl<Integer>(input.get()+100);
	}

}
