package de.flapdoodle.einkauf

/*
 * Copyright (C) 2017 Google Inc.
 *
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
 */

import androidx.lifecycle.LiveData

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class ItemRepository(private val itemDao: ItemDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allItems: LiveData<List<Item>> = itemDao.getAllItems()

    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    // This ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    fun updatedAmount(id: Int, amount: Int) {
        val current = itemDao.getItem(id)
        itemDao.update(current.copy(amount=amount))
    }

    fun updateActive(id: Int, active: Boolean) {
        val current = itemDao.getItem(id)
        itemDao.update(current.copy(active=active))
    }

    fun delete(id: Int) {
        itemDao.delete(id)
    }

    fun updateNameAndPrice(id: Int, name: String, price: Int) {
        val current = itemDao.getItem(id)
        itemDao.update(current.copy(name= name, unitPriceCent = price))
    }
}
