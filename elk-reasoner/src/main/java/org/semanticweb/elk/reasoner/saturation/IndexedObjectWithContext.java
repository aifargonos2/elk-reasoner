package org.semanticweb.elk.reasoner.saturation;

/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2014 Department of Computer Science, University of Oxford
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.semanticweb.elk.reasoner.indexing.hierarchy.IndexedObject;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * An {@link IndexedObject} to which a {@link Context} can be assigned. In some
 * situations this is more efficient than using a map.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public abstract class IndexedObjectWithContext extends IndexedObject {

	/**
	 * the reference to a {@link Context} assigned to this {@link IndexedObject}
	 */
	private volatile ExtendedContext context_ = null;

	/**
	 * @return The corresponding context, null if none was assigned.
	 */
	ExtendedContext getContext() {
		return context_;
	}

	/**
	 * Assign the given {@link ExtendedContext} to this {@link IndexedObject} if
	 * none was yet assigned.
	 * 
	 * @param context
	 *            the {@link ExtendedContext} which will be assigned to this
	 *            {@link IndexedObject}
	 * 
	 * @return the previously assigned {@link ExtendedContext} or {@code null}
	 *         if none was assigned (in which case the new
	 *         {@link ExtendedContext} will be assigned)
	 */
	synchronized ExtendedContext setContextIfAbsent(ExtendedContext context) {
		if (context_ != null)
			return context_;
		// else
		context_ = context;
		return null;
	}

	/**
	 * Resets the corresponding context to {@code null}.
	 */
	synchronized void resetContext() {
		context_ = null;
	}

}
