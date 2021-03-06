/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.gearpump.streaming.kafka.util

import kafka.utils.{ZKStringSerializer, Utils, TestZKUtils}
import kafka.zk.EmbeddedZookeeper
import org.I0Itec.zkclient.ZkClient

trait ZookeeperHarness {
  val zkConnect: String = TestZKUtils.zookeeperConnect
  val zkConnectionTimeout = 60000
  val zkSessionTimeout = 60000
  private var zookeeper: EmbeddedZookeeper = null

  def getZookeeper: EmbeddedZookeeper = zookeeper
  def newZkClient: ZkClient = {
    new ZkClient(zkConnect, zkSessionTimeout, zkConnectionTimeout, ZKStringSerializer)
  }

  def setUp() {
    zookeeper = new EmbeddedZookeeper(zkConnect)
  }

  def tearDown() {
    Utils.swallow(zookeeper.shutdown())
  }
}
