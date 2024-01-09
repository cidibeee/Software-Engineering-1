package org.hbrs.se1.ws23.uebung8;

import javax.management.Query;

public class Adapter {
    Result adaptExecuteQuery(Query d)
    {
        QueryObject oldData = transformInput(d);
        QueryResult oldResult = Reiseanbieter.executeQuery(oldData);

        Result result = transformOutput(oldResult);
        return result;
    }

    private Result transformOutput(QueryResult oldResult)
    {
        //Do Something
        return new Result();
    }

    private QueryObject transformInput(Query d) {
        //Do Sopmething
        return new QueryObject();
    }
}
