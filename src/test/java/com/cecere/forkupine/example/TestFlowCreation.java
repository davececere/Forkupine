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
import com.cecere.forkupine.process.execute.DepthFirstSerialExecutorFactory;
import com.cecere.forkupine.process.execute.ExecutorFactory;
import com.cecere.forkupine.data.None;
import com.cecere.forkupine.data.Some;
import com.cecere.forkupine.data.SomeImpl;
import com.cecere.forkupine.execute.SpineExecutor;

import com.cecere.forkupine.example.spine.*;

public class TestFlowCreation {
	ExecutorFactory factory = new DepthFirstSerialExecutorFactory();

	@Test
	public void testCreateFlow(){
		SpineExecutor<Some<String>,Some<Integer>> executor = null;
		
		Spine<Some<String>,Some<Integer>> head = factory.spineExecutor(new StringLengthSpine());
		Spine<Some<Integer>,Some<Float>> next1 = factory.spineExecutor(new DivideBy1000Spine());
		Spine<Some<Integer>,Some<Integer>> next2 = factory.spineExecutor(new Add100Spine());
		Spine2<Some<Float>,Some<Integer>,None> tail = factory.spine2Executor(new LoggingSpine());
		
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
		
		Some<Integer> nullOutPut = head.process(testString); //weird about the null output
	}
	
}
