package service;

import java.util.Collection;
import java.util.concurrent.Callable;

import domain.Proxy;

public interface IFetchable extends Callable<Collection<Proxy>> {
}
