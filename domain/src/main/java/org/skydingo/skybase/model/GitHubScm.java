/* 
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
package org.skydingo.skybase.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * GitHub SCM entity.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement
@XmlType(propOrder = { "user", "repo" })
public class GitHubScm extends AbstractCI<GitHubScm>  {
	private String user;
	private String repo;
	
	// GitHub fields
	private Long id;
	private String name;
	private String description;
	private String url;
	private String htmlUrl;
	private String cloneUrl;
	private String gitUrl;
	private String sshUrl;
	private String svnUrl;
	
	public GitHubScm() { }
	
	public GitHubScm(String user, String repo) {
		this.user = user;
		this.repo = repo;
	}
	
	/**
	 * @return
	 */
	@NotNull
	@Size(max = 80) // FIXME Find out the real limit
	@XmlElement
	public String getUser() { return user; }
	
	/**
	 * @param user
	 */
	public void setUser(String user) { this.user = user; }
	
	/**
	 * @return
	 */
	@NotNull
	@Size(max = 80) // FIXME Find out the real limit
	@XmlElement
	public String getRepo() { return repo; }
	
	/**
	 * @param repo
	 */
	public void setRepo(String repo) { this.repo = repo; }
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the htmlUrl
	 */
	public String getHtmlUrl() {
		return htmlUrl;
	}

	/**
	 * @param htmlUrl the htmlUrl to set
	 */
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	/**
	 * @return the cloneUrl
	 */
	public String getCloneUrl() {
		return cloneUrl;
	}

	/**
	 * @param cloneUrl the cloneUrl to set
	 */
	public void setCloneUrl(String cloneUrl) {
		this.cloneUrl = cloneUrl;
	}

	/**
	 * @return the gitUrl
	 */
	public String getGitUrl() {
		return gitUrl;
	}

	/**
	 * @param gitUrl the gitUrl to set
	 */
	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	/**
	 * @return the sshUrl
	 */
	public String getSshUrl() {
		return sshUrl;
	}

	/**
	 * @param sshUrl the sshUrl to set
	 */
	public void setSshUrl(String sshUrl) {
		this.sshUrl = sshUrl;
	}

	/**
	 * @return the svnUrl
	 */
	public String getSvnUrl() {
		return svnUrl;
	}

	/**
	 * @param svnUrl the svnUrl to set
	 */
	public void setSvnUrl(String svnUrl) {
		this.svnUrl = svnUrl;
	}

	/* (non-Javadoc)
	 * @see org.skydingo.skybase.model.Entity#getDisplayName()
	 */
	@Override
	public String getDisplayName() { return user + "/" + repo; }
}
