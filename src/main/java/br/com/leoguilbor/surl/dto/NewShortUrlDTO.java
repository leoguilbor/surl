/**
 *     This is a class from model Tier for Category entity.
 *     Copyright (C) 2018 Leandro Lima
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.leoguilbor.surl.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.leoguilbor.surl.domain.ShortUrl;

public class NewShortUrlDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String url;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public NewShortUrlDTO() {
	}

	public NewShortUrlDTO(ShortUrl surl) {
		super();
		this.url = surl.getUrl();
		this.createdAt = Calendar.getInstance().getTime();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ShortUrl ToObject() {
		return new ShortUrl(null, this.url, null, this.createdAt,null);
	}
}
