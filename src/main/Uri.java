package main;

import java.net.URI;
import java.net.URISyntaxException;

public class Uri {
	String baseURI;
	String relativeURI;
	String resolvedURI;
	String relativizedURI;
	String normalizeUri;
	String scheme;
	String rawScheme;
	String schemeSpecificPart;
	String rawUserInfo;
	String userInfo;
	String authority;
	String rawAuthority;
	String host;
	int port;
	String rawPath;
	String path;
	String rawQuery;
	String query;
	String rawFragment;
	String fragment;
	String compareTo;
	boolean equals;
	String hashcode;
	String toString;
	String toASCIIString;
	
	public Uri(String strUriRelative ) {
		String defaultProtocol = "http://";
		String defaultHost = "localhost/";
		String strUriBase = defaultProtocol+defaultHost;
		String uriFinal = defaultProtocol+defaultHost+strUriRelative;
		try {
			URI uri = URI.create(uriFinal);
			URI uriBase = new URI(strUriBase);
			URI uriRelative = new URI(strUriRelative); 
			URI uriResolved = uriBase.resolve(uriRelative); 
			URI uriRelativized = uriBase.relativize(uriResolved); 
			
			//SetURI
			setBaseURI(uriBase.toString());	
			setRelativeURI(uriRelative.toString());
			setResolvedURI(uriResolved.toString());
			setRelativizedURI(uriRelativized.toString());
			setNormalizeUri(uri.normalize().toString());
			setScheme(uri.getScheme().toString());
			setRawScheme(uri.getRawSchemeSpecificPart());
			setSchemeSpecificPart(uri.getSchemeSpecificPart());
			setRawUserInfo(uri.getRawUserInfo());
			setUserInfo(uri.getUserInfo());
			setAuthority(uri.getAuthority());
			setRawAuthority(uri.getRawAuthority());
			setHost(uri.getHost());
			setPort(uri.getPort());
			setPath(uri.getPath());
			setRawPath(uri.getRawPath());
			setQuery(uri.getQuery());
			setRawQuery(uri.getRawQuery());
			setFragment(uri.getFragment());
			setRawFragment(uri.getRawFragment());
			
		} catch (URISyntaxException e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	public String getBaseURI() {
		return baseURI;
	}




	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}




	public String getRelativeURI() {
		return relativeURI;
	}




	public void setRelativeURI(String relativeURI) {
		this.relativeURI = relativeURI;
	}




	public String getResolvedURI() {
		return resolvedURI;
	}




	public void setResolvedURI(String resolvedURI) {
		this.resolvedURI = resolvedURI;
	}




	public String getRelativizedURI() {
		return relativizedURI;
	}




	public void setRelativizedURI(String relativizedURI) {
		this.relativizedURI = relativizedURI;
	}




	public String getNormalizeUri() {
		return normalizeUri;
	}




	public void setNormalizeUri(String normalizeUri) {
		this.normalizeUri = normalizeUri;
	}




	public String getScheme() {
		return scheme;
	}




	public void setScheme(String scheme) {
		this.scheme = scheme;
	}




	public String getRawScheme() {
		return rawScheme;
	}




	public void setRawScheme(String rawScheme) {
		this.rawScheme = rawScheme;
	}




	public String getSchemeSpecificPart() {
		return schemeSpecificPart;
	}




	public void setSchemeSpecificPart(String schemeSpecificPart) {
		this.schemeSpecificPart = schemeSpecificPart;
	}

	
	
	
	public String getRawUserInfo() {
		return rawUserInfo;
	}




	public void setRawUserInfo(String rawUserInfo) {
		this.rawUserInfo = rawUserInfo;
	}

	
	

	public String getUserInfo() {
		return userInfo;
	}




	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}




	public String getAuthority() {
		return authority;
	}




	public void setAuthority(String authority) {
		this.authority = authority;
	}




	public String getRawAuthority() {
		return rawAuthority;
	}




	public void setRawAuthority(String rawAuthority) {
		this.rawAuthority = rawAuthority;
	}




	public String getHost() {
		return host;
	}




	public void setHost(String host) {
		this.host = host;
	}




	public int getPort() {
		return port;
	}




	public void setPort(int port) {
		this.port = port;
	}




	public String getRawPath() {
		return rawPath;
	}




	public void setRawPath(String rawPath) {
		this.rawPath = rawPath;
	}




	public String getPath() {
		return path;
	}




	public void setPath(String path) {
		this.path = path;
	}




	public String getRawQuery() {
		return rawQuery;
	}




	public void setRawQuery(String rawQuery) {
		this.rawQuery = rawQuery;
	}




	public String getQuery() {
		return query;
	}




	public void setQuery(String query) {
		this.query = query;
	}




	public String getRawFragment() {
		return rawFragment;
	}




	public void setRawFragment(String rawFragment) {
		this.rawFragment = rawFragment;
	}




	public String getFragment() {
		return fragment;
	}




	public void setFragment(String fragment) {
		this.fragment = fragment;
	}




	public String getCompareTo() {
		return compareTo;
	}




	public void setCompareTo(String compareTo) {
		this.compareTo = compareTo;
	}




	public boolean isEquals() {
		return equals;
	}




	public void setEquals(boolean equals) {
		this.equals = equals;
	}




	public String getHashcode() {
		return hashcode;
	}




	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}




	public String getToString() {
		return toString;
	}




	public void setToString(String toString) {
		this.toString = toString;
	}




	public String getToASCIIString() {
		return toASCIIString;
	}




	public void setToASCIIString(String toASCIIString) {
		this.toASCIIString = toASCIIString;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
