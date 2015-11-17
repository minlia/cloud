/**
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/framework/2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minlia.cloud.framework.web.validation;

import java.util.Locale;

/**
 * Provides service methods to export validation constraints configured on a given model object.
 *
 * This interface is validation provider agnostic and could be implemented to support any kind of
 * validation constraint API
 */
public interface ValidationService {

    /**
     * Find validation constraints for a model class name and return their representation
     *
     * @param canonicalClassName complete class name of the model to find
     *
     * @return a {@link ModelConstraint} wrapper containing all validation
     * constraints retrieved for the given <tt>canonicalClassName</tt>
     *
     * @throws ClassNotFoundException if <tt>canonicalClassName</tt> does not match any class
     */
    ModelConstraint getConstraintsForClassName(String canonicalClassName) throws ClassNotFoundException;

    /**
     * Find validation constraints for a model class name and return their representation.
     * This method also enhances constraints with concrete resolved constraint error message depending
     * on the given <tt>locale</tt>
     *
     * @param canonicalClassName complete class name of the model to find
     * @param locale {@link Locale} object used to resolve localized messages, if any
     *
     * @return a {@link ModelConstraint} wrapper containing all validation
     * constraints retrieved for the given <tt>canonicalClassName</tt>
     *
     * @throws ClassNotFoundException if <tt>canonicalClassName</tt> does not match any class
     */
    ModelConstraint getConstraintsForClassName(String canonicalClassName, Locale locale) throws ClassNotFoundException;

    /**
     * Find validation constraints for a model class and return their representation
     *
     * @param clazz class of the model to find
     *
     * @return a {@link ModelConstraint} wrapper containing all validation
     * constraints retrieved for the given <tt>clazz</tt>
     */
    ModelConstraint getConstraintsForClass(Class<?> clazz);

    /**
     * Find validation constraints for a model class and return their representation.
     * This method also enhances constraints with concrete resolved constraint error message depending
     * on the given <tt>locale</tt>
     *
     * @param clazz class of the model to find
     * @param locale {@link Locale} object used to resolve localized messages, if any
     *
     * @return a {@link ModelConstraint} wrapper containing all validation
     * constraints retrieved for the given <tt>clazz</tt>
     */
    ModelConstraint getConstraintsForClass(Class<?> clazz, Locale locale);
}
