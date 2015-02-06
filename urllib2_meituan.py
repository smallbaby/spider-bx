#!/usr/bin/env python

import urllib2
def fetch(url):
	http_header = {"User-Agent":"Chrome"}
	http_request = urllib2.Request(url,None,http_header)

	http_response = urllib2.urlopen(http_request)
	print "Finsh"
	print http_response.code
	print http_response.info()
	print http_response.read()
if __name__ == "__main__":
	fetch("http://www.meituan.com/api/v2/beijing/deals")
