/* Copyright (C) 2013 sumito3478 <sumito3478@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.sumito3478.aprikot.threading

import java.lang.{ ThreadLocal => JThreadLocal }

/**
 * A trait that represents a thread local storage.
 *
 * Use ThreadLocal.apply to create an instance of this trait.
 */
trait ThreadLocal[A] {
  def apply(): A
}

object ThreadLocal {
  /**
   * Creates an instance of ThreadLocal[A].
   */
  def apply[A](f: => A): ThreadLocal[A] = {
    val underlined = new JThreadLocal[A] {
      override def initialValue: A = {
        f
      }
    }
    new ThreadLocal[A] {
      def apply: A = {
        underlined.get
      }
    }
  }
}
