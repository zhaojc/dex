package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.IteratorException;

public class SearchUtils {

    public static void storeSearchSessionObject(HttpServletRequest request,
                                ValueListIterator results,
                                Collection currentList,
                                long nbOfPages,
                                String completeListsessionKey,
                                String currentListsessionKey,
                                String currentPageSessionKey,
                                String numberOfPagesSessionKey,
                                String hasNextSessionKey,
                                String hasPreviousSessionKey
                                ) throws IteratorException,
                                         IOException,
                                         ServletException {
            Long maxNumberOfPages = new Long(nbOfPages);
            Long currentPage = new Long(1);

            request.getSession().setAttribute(completeListsessionKey,results);
            request.getSession().setAttribute(currentListsessionKey,currentList);
            request.getSession().setAttribute(currentPageSessionKey,currentPage);
            request.getSession().setAttribute(numberOfPagesSessionKey,maxNumberOfPages);
            request.getSession().setAttribute(hasNextSessionKey,""+results.hasNext());
            results.getPreviousElements(currentList.size());
            request.getSession().setAttribute(hasPreviousSessionKey,""+results.hasPrevious());
    }

   public static void storeSearchFirstSessionObject(HttpServletRequest request,
                                ValueListIterator results,
                                Collection currentList,
                                String completeListsessionKey,
                                String currentListsessionKey,
                                String currentPageSessionKey,
                                String hasNextSessionKey,
                                String hasPreviousSessionKey
                                ) throws IteratorException,
                                         IOException,
                                         ServletException {
            Long currentPage = new Long(1);
            request.getSession().setAttribute(completeListsessionKey,results);
            request.getSession().setAttribute(currentListsessionKey,currentList);
            request.getSession().setAttribute(currentPageSessionKey,currentPage);
            request.getSession().setAttribute(hasNextSessionKey,""+results.hasNext());
            results.getPreviousElements(currentList.size());
            request.getSession().setAttribute(hasPreviousSessionKey,""+results.hasPrevious());
    }

   public static void storeSearchLastSessionObject(HttpServletRequest request,
                                ValueListIterator results,
                                Collection currentList,
                                String completeListsessionKey,
                                String currentListsessionKey,
                                String currentPageSessionKey,
                                String numberOfPagesSessionKey,
                                String hasNextSessionKey,
                                String hasPreviousSessionKey
                                ) throws IteratorException,
                                         IOException,
                                         ServletException {
            Long currentPage = (Long)request.getSession().getAttribute(numberOfPagesSessionKey);

            request.getSession().setAttribute(completeListsessionKey,results);
            request.getSession().setAttribute(currentListsessionKey,currentList);
            request.getSession().setAttribute(currentPageSessionKey,currentPage);
            request.getSession().setAttribute(hasNextSessionKey,""+results.hasNext());
            results.getPreviousElements(currentList.size());
            request.getSession().setAttribute(hasPreviousSessionKey,""+results.hasPrevious());
    }

   public static void storeSearchNextSessionObject(HttpServletRequest request,
                                ValueListIterator results,
                                Collection currentList,
                                String completeListsessionKey,
                                String currentListsessionKey,
                                String currentPageSessionKey,
                                String hasNextSessionKey,
                                String hasPreviousSessionKey
                                ) throws IteratorException,
                                         IOException,
                                         ServletException {
            Long currentPage = (Long)request.getSession().getAttribute(currentPageSessionKey);
            currentPage = new Long(currentPage.longValue()+1);

            request.getSession().setAttribute(completeListsessionKey,results);
            request.getSession().setAttribute(currentListsessionKey,currentList);
            request.getSession().setAttribute(currentPageSessionKey,currentPage);
            request.getSession().setAttribute(hasNextSessionKey,""+results.hasNext());
            results.getPreviousElements(currentList.size());
            request.getSession().setAttribute(hasPreviousSessionKey,""+results.hasPrevious());
    }

   public static void storeSearchPreviousSessionObject(HttpServletRequest request,
                                ValueListIterator results,
                                Collection currentList,
                                String completeListsessionKey,
                                String currentListsessionKey,
                                String currentPageSessionKey,
                                String hasNextSessionKey,
                                String hasPreviousSessionKey
                                ) throws IteratorException,
                                         IOException,
                                         ServletException {
            Long currentPage = (Long)request.getSession().getAttribute(currentPageSessionKey);
            currentPage = new Long(currentPage.longValue()-1);

            request.getSession().setAttribute(completeListsessionKey,results);
            request.getSession().setAttribute(currentListsessionKey,currentList);
            request.getSession().setAttribute(currentPageSessionKey,currentPage);
            request.getSession().setAttribute(hasNextSessionKey,""+results.hasNext());
            request.getSession().setAttribute(hasPreviousSessionKey,""+results.hasPrevious());
    }

}