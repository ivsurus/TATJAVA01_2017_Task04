package com.epam.catalog.controller;


/**
  * init() is the first method should be called on Controller object
  * destroy() should be called in the end of life circle of Controller object
 */

interface LifeCircle {
    String init();
    String destroy();
}
