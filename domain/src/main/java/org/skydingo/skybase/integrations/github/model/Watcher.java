/* 
 * Watcher.java
 * 
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.skydingo.skybase.integrations.github.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

// FIXME Figure out why I'm having to use @JsonProperty here. I would have expected to be able to get away with using
// @XmlElement since we're using the JAXB annotation inspector. (Verify that it's being used.)

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
public class Watcher {
	private Long id;
	private String url;
	private String login;
	private String avatarUrl;
	private String gravatarId;
	
	/**
	 * @return
	 */
	@XmlElement
	public Long getId() { return id; }
	
	/**
	 * @param id
	 */
	public void setId(Long id) { this.id = id; }
	
	/**
	 * @return
	 */
	@XmlElement
	public String getUrl() { return url; }
	
	/**
	 * @param url
	 */
	public void setUrl(String url) { this.url = url; }
	
	/**
	 * @return
	 */
	@XmlElement
	public String getLogin() { return login; }
	
	/**
	 * @param login
	 */
	public void setLogin(String login) { this.login = login; }
	
	/**
	 * @return
	 */
	@XmlElement(name = "avatar_url")
	@JsonProperty("avatar_url")
	public String getAvatarUrl() { return avatarUrl; }
	
	/**
	 * @param avatarUrl
	 */
	public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
	
	/**
	 * @return
	 */
	@XmlElement(name = "gravatar_id")
	@JsonProperty("gravatar_id")
	public String getGravatarId() { return gravatarId; }
	
	/**
	 * @param gravatarId
	 */
	public void setGravatarId(String gravatarId) { this.gravatarId = gravatarId; }
	
//	@XmlRootElement(name = "watchers")
//	public static class WatcherListWrapper {
//		private List<Watcher> list;
//		
//		@XmlElement(name = "watcher")
//		public List<Watcher> getList() { return list; }
//
//		public void setList(List<Watcher> list) { this.list = list; }
//	}
}
