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
import com.cecere.forkupine.data.process.DataFlowProcessor;
import com.cecere.forkupine.data.process.DataFlowProcessor2;
import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.Spine2;

/**
 * @author dave
 *
 */
public class ExecutorFactoryImpl implements ExecutorFactory {

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.execute.ExecutorFactory#spineExecutor(com.cecere.forkupine.process.Spine)
	 */
	public <I extends Data, O extends Data> Spine<I, O> serialSpineExecutor(DataFlowProcessor<I, O> rawSpine) {
		return new DepthFirstSerialExecutingSpine(rawSpine);
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.execute.ExecutorFactory#spine2Executor(com.cecere.forkupine.process.Spine2)
	 */
	public <A extends Data, B extends Data, O extends Data> Spine2<A, B, O> serialSpine2Executor(DataFlowProcessor2<A, B, O> rawSpine) {
		return new DepthFirstSerialExecutingSpine2(rawSpine);
	}

	/* (non-Javadoc)
	 * @see com.cecere.forkupine.process.execute.ExecutorFactory#isolatedSpineExecutor(com.cecere.forkupine.data.process.DataFlowProcessor)
	 */
	@Override
	public <I extends Data, O extends Data> Spine<I, O> isolatedSpineExecutor(
			DataFlowProcessor<I, O> rawSpine) {
		return new IsolatedExecutingSpine(rawSpine);
	}

}
