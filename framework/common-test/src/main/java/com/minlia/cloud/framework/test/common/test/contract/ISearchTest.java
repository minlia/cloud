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
package com.minlia.cloud.framework.test.common.test.contract;

public interface ISearchTest {

    void whenSearchByNameIsPerformed_thenNoExceptions();

    // id

    void givenResourceWithIdExists_whenResourceIsSearchedById_thenNoExceptions();

    void givenResourceWithIdExists_whenResourceIsSearchedById_thenSearchOperationIsSuccessful();

    void givenResourceWithIdExists_whenResourceIsSearchedById_thenResourceIsFound();

    void givenResourceWithIdDoesNotExist_whenResourceIsSearchedById_thenResourceIsNotFound();

    // name

    void givenResourceWithNameExists_whenResourceIsSearchedByName_thenNoExceptions();

    void givenResourceWithNameExists_whenResourceIsSearchedByName_thenOperationIsSuccessful();

    void givenResourceWithNameExists_whenResourceIsSearchedByName_thenResourceIsFound();

    void givenResourceWithNameExists_whenResourceIsSearchedByNameLowerCase_thenResourceIsFound();

    void givenResourceWithNameDoesNotExist_whenResourceIsSearchedByName_thenResourceIsNotFound();

    void givenResourceWithNameExists_whenSearchByNegatedNameIsPerformed_thenResourcesAreCorrect();

    // id and name

    void givenResourceWithNameAndIdExists_whenResourceIsSearchedByCorrectIdAndCorrectName_thenOperationIsSuccessful();

    void givenResourceWithNameAndIdExists_whenResourceIsSearchedByCorrectIdAndCorrectName_thenResourceIsFound();

    void givenResourceWithNameAndIdExists_whenResourceIsSearchedByIncorrectIdAndCorrectName_thenResourceIsNotFound();

    void givenResourceWithNameAndIdExists_whenResourceIsSearchedByCorrectIdAndIncorrectName_thenResourceIsNotFound();

    void givenResourceWithNameAndIdExists_whenResourceIsSearchedByIncorrectIdAndIncorrectName_thenResourceIsNotFound();

    // negated

    void givenResourceExists_whenResourceIsSearchedByNegatedName_thenOperationIsSuccessful();

    void givenResourceExists_whenResourceIsSearchedByNegatedId_thenOperationIsSuccessful();

    void givenResourceExists_whenResourceIsSearchedByNegatedId_thenResourceIsNotFound();

    void givenResourcesExists_whenResourceIsSearchedByNegatedId_thenTheOtherResourcesAreFound();

    void givenResourceAndOtherResourcesExists_whenResourceIsSearchedByNegatedName_thenResourcesAreFound();

    void givenResourceAndOtherResourcesExists_whenResourceIsSearchedByNegatedId_thenResourcesAreFound();

    // contains

    void givenResourceWithNameExists_whenResourceIsSearchedByContainsExactName_thenNoExceptions();

    void givenResourceWithNameExists_whenSearchByContainsEntireNameIsPerformed_thenResourceIsFound();

    void givenResourceWithNameExists_whenSearchByContainsPartOfNameIsPerformed_thenResourceIsFound();

    // starts/ends with

    void whenSearchByStartsWithIsPerformed_thenNoExceptions();

    void givenResourceExists_whenSearchByStartsWithEntireNameIsPerformed_thenResourceIsFound();

    void givenResourceExists_whenSearchByStartsWithPartOfNameIsPerformed_thenResourceIsFound();

    void whenSearchByEndsWithIsPerformed_thenNoExceptions();

    void givenResourceExists_whenSearchByEndsWithEntireNameIsPerformed_thenResourceIsFound();

    void givenResourceExists_whenSearchByEndsWithPartOfNameIsPerformed_thenResourceIsFound();

}
