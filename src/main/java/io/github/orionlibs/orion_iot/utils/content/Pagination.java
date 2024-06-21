package io.github.orionlibs.orion_iot.utils.content;

public class Pagination
{
    private int pageNumber;
    private int paginationLimit;
    private int offset;


    public Pagination(int pageNumber, int paginationLimit)
    {
        this.pageNumber = pageNumber;
        this.paginationLimit = paginationLimit;
        this.offset = (pageNumber - 1) * paginationLimit;
    }


    public static Pagination ofLimit1()
    {
        return new Pagination(1, 1);
    }


    public static Pagination ofLimit(int paginationLimit)
    {
        return new Pagination(1, paginationLimit);
    }


    public static Pagination of(int pageNumber, int paginationLimit)
    {
        return new Pagination(pageNumber, paginationLimit);
    }


    public static long getTotalNumberOfPages(long numberOfElements, int paginationLimit)
    {
        if(numberOfElements < 1)
        {
            return 0L;
        }
        else if(numberOfElements % paginationLimit == 0)
        {
            return numberOfElements / paginationLimit;
        }
        else
        {
            long x = (numberOfElements / paginationLimit) + 1;
            return x;
        }
    }


    public int getPageNumber()
    {
        return this.pageNumber;
    }


    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }


    public int getPaginationLimit()
    {
        return this.paginationLimit;
    }


    public void setPaginationLimit(int paginationLimit)
    {
        this.paginationLimit = paginationLimit;
    }


    public int getOffset()
    {
        return this.offset;
    }


    public void setOffset(int offset)
    {
        this.offset = offset;
    }
}