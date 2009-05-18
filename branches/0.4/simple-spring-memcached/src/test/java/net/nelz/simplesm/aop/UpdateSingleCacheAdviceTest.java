package net.nelz.simplesm.aop;

import net.nelz.simplesm.annotations.*;
import org.testng.annotations.*;

import java.lang.reflect.*;

/**
Copyright (c) 2008  Nelson Carpentier

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
public class UpdateSingleCacheAdviceTest {
	private UpdateSingleCacheAdvice cut;

	@BeforeClass
	public void beforeClass() {
		cut = new UpdateSingleCacheAdvice();
	}

	@Test
	public void testAnnotationValidator() throws Exception {
		final AnnotationValidator testClass = new AnnotationValidator();
		Method method = testClass.getClass().getMethod("cacheMe1",null);
		UpdateSingleCache annotation = method.getAnnotation(UpdateSingleCache.class);
		cut.validateAnnotation(annotation, method);
	}

	private static class AnnotationValidator {
		@UpdateSingleCache(keyIndex = -1, namespace = "bubba")
		public String cacheMe1() { return null; }
	}

}