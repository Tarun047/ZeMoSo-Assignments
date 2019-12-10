package com.tarun.talkbuddy.helpers;

import com.tarun.talkbuddy.daos.interfaces.GenericDao;
import com.tarun.talkbuddy.model.Model;
import org.mockito.Mockito;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class GenericDaoMocker<T extends Model>
{
    private Map<Long,T> localStorage;
    public GenericDaoMocker(GenericDao obj)
    {
      localStorage = new HashMap<>();
      Mockito.when(obj.list()).thenAnswer(invocation ->
      {
          List<T> retVal = new ArrayList<>(localStorage.values());
          return retVal;
      });
      Mockito.when(obj.save(any())).thenAnswer(
              invocation -> {
                  T param = invocation.getArgument(0);
                  localStorage.put(param.getId(),param);
                  return param;
              }
      );
      Mockito.when(obj.remove(any())).thenAnswer(
        invocation -> {
            long id = invocation.getArgument(0);
            if(localStorage.containsKey(id))
            {
                localStorage.remove(id);
                return true;
            }
            return false;
        }
      );
      Mockito.when(obj.find(any())).thenAnswer(
              invocation -> {
                  long id = invocation.getArgument(0);
                  Optional<T> retVal = Optional.ofNullable(localStorage.get(id));
                  return retVal;
              }
      );

    }
}
