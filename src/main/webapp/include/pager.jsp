<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="font-size:11pt;padding-top:2px;">
<input type="hidden" id="pageControl_pageSize" name="pageControl.pageSize" value="${pageControl.pageSize}" size="3"/>
<input type="hidden" id="pageControl_pageCurrentNum" name="pageControl.pageCurrentNum" value="${pageControl.pageCurrentNum }" size="3"/>
<input type="hidden" id="pageControl_pageCount" name="pageControl.pageCount" value="${pageControl.pageCount}" size="3"/>
<input type="button" id="pageFirst" value="<<" size="1" title="第1頁" class="btnPager" /><input type="button" id="pagePrev" value="<" size="1"  title="上一頁" class="btnPager" />
${pageControl.pageCurrentNum}/${pageControl.pageCount}
<input type="button" id="pageNext" value=">" size="1"  title="下一頁" class="btnPager" /><input type="button" id="pageLast" value=">>" size="1" title="最後1頁" class="btnPager" />
</div>