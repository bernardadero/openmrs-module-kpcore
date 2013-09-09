/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.kenyacore;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.openmrs.OpenmrsObject;
import org.openmrs.util.OpenmrsUtil;

/**
 * Abstract base class for entity descriptors
 */
public abstract class AbstractEntityDescriptor<T extends OpenmrsObject> extends AbstractDescriptor implements Comparable<AbstractEntityDescriptor> {

	protected String targetUuid;

	protected Integer order;

	/**
	 * Gets the target object UUID
	 * @return the target object UUID
	 */
	public String getTargetUuid() {
		return targetUuid;
	}

	/**
	 * Sets the target object UUID
	 * @param targetUuid the target object UUID
	 */
	public void setTargetUuid(String targetUuid) {
		this.targetUuid = targetUuid;
	}

	/**
	 * Gets the target object
	 * @return the target object
	 */
	public abstract T getTarget();

	/**
	 * Gets the order
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * Sets the order
	 * @param order the order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @see Comparable#compareTo(Object)
	 */
	@Override
	public int compareTo(AbstractEntityDescriptor descriptor) {
		int byOrder = OpenmrsUtil.compareWithNullAsGreatest(order, descriptor.order);

		// Return by id if order is equal. Important to not return zero from this method unless the objects
		// are actually equal. Otherwise TreeSet sees them as equal.
		return byOrder != 0 ? byOrder : id.compareTo(descriptor.id);
	}

	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AbstractEntityDescriptor)) return false;

		AbstractEntityDescriptor that = (AbstractEntityDescriptor) o;

		return targetUuid.equals(that.targetUuid);
	}

	/**
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return targetUuid.hashCode();
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", id)
				.append("targetUuid", targetUuid)
				.toString();
	}
}