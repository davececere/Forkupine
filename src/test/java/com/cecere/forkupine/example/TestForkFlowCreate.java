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
package com.cecere.forkupine.example;

import jsr166y.ForkJoinPool;
import jsr166y.RecursiveTask;

import org.junit.Test;

import com.cecere.forkupine.data.None;
import com.cecere.forkupine.data.Some;
import com.cecere.forkupine.data.SomeImpl;
import com.cecere.forkupine.example.spine.Add100Spine;
import com.cecere.forkupine.example.spine.DivideBy1000Spine;
import com.cecere.forkupine.example.spine.LoggingFloatIntSpine;
import com.cecere.forkupine.example.spine.LoggingIntSpine;
import com.cecere.forkupine.example.spine.StringLengthSpine;
import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.Spine2;
import com.cecere.forkupine.process.SpineImpl;
import com.cecere.forkupine.process.execute.ExecutorFactoryImpl;
import com.cecere.forkupine.process.execute.ExecutorFactory;
import com.cecere.forkupine.process.forkjoin.RecursiveSpineImpl;
import com.cecere.forkupine.process.parameter.IdentitySpine;

/**
 * @author dave
 *
 */
public class TestForkFlowCreate {
	ExecutorFactory factory = new ExecutorFactoryImpl();

	@Test
	public void testCreateLinearFlow(){
		
		Spine<Some<String>,Some<String>> input = new IdentitySpine<String>("yo ho ho and a bottle of rum");
		Spine<Some<String>,Some<Integer>> length = factory.isolatedSpineExecutor(new StringLengthSpine());
		Spine<Some<Integer>,Some<Integer>> add100 = factory.isolatedSpineExecutor(new Add100Spine());
		Spine<Some<Integer>,Some<Integer>> add100again = factory.isolatedSpineExecutor(new Add100Spine());
		Spine<Some<Integer>,None> printInt = factory.isolatedSpineExecutor(new LoggingIntSpine());
		
		input.flowsInto(
		   length.flowsInto(
				add100.flowsInto(
						add100again.flowsInto(
								printInt
								))));
		
		RecursiveTask<None> tail = new RecursiveSpineImpl<Some<Integer>,None>(printInt);
		ForkJoinPool pool = new ForkJoinPool(2);
		None output = pool.invoke(tail);
		pool.shutdown();
	}
}
