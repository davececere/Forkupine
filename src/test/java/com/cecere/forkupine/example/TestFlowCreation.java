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

import org.junit.Test;

import com.cecere.forkupine.process.Spine;
import com.cecere.forkupine.process.Spine2;
import com.cecere.forkupine.process.execute.ExecutorFactoryImpl;
import com.cecere.forkupine.process.execute.ExecutorFactory;
import com.cecere.forkupine.data.None;
import com.cecere.forkupine.data.Some;
import com.cecere.forkupine.data.SomeImpl;

import com.cecere.forkupine.example.spine.*;

public class TestFlowCreation {
	ExecutorFactory factory = new ExecutorFactoryImpl();

	@Test
	public void testCreateFlow(){
		
		Spine<Some<String>,Some<Integer>> head = factory.serialSpineExecutor(new StringLengthSpine());
		Spine<Some<Integer>,Some<Float>> next1 = factory.serialSpineExecutor(new DivideBy1000Spine());
		Spine<Some<Integer>,Some<Integer>> next2 = factory.serialSpineExecutor(new Add100Spine());
		Spine2<Some<Float>,Some<Integer>,None> tail = factory.serialSpine2Executor(new LoggingFloatIntSpine());
		
		Some<String> testString = new SomeImpl<String>("yo ho ho and a bottle of rum");
		
		head.flowsInto(
			next1.flowsInto(
				tail.asParam1()
			)
		).flowsInto(
			next2.flowsInto(
				tail.asParam2()
			)
		);
		
		head.process(testString);
	}
	
	@Test
	public void testCreateFlow2(){
		
		Spine<Some<String>,Some<Integer>> head = factory.serialSpineExecutor(new StringLengthSpine());
		Spine<Some<Integer>,Some<Float>> next1 = factory.serialSpineExecutor(new DivideBy1000Spine());
		Spine<Some<Integer>,Some<Integer>> next2 = factory.serialSpineExecutor(new Add100Spine());
		Spine2<Some<Float>,Some<Integer>,Some<Integer>> tail = factory.serialSpine2Executor(new LoggingFloatIntSpineReturnLengthPrinted());
		Spine<Some<Integer>,Some<Integer>> afterTail = factory.serialSpineExecutor(new Add100Spine());
		Spine<Some<Integer>,None> printInt = factory.serialSpineExecutor(new LoggingIntSpine());
		
		Some<String> testString = new SomeImpl<String>("yo ho ho and a bottle of rum");
		
		head.flowsInto(
			next1.flowsInto(
				tail.asParam1()
			)
		).flowsInto(
			next2.flowsInto(
				tail.asParam2()
			)
		);
		
		//need to start tree over for spine2s instead of using flowsInto after asParam() calls. should we fix this?
		tail.flowsInto(
				afterTail.flowsInto(printInt)
		);
		
		head.process(testString);
	}
	
}
