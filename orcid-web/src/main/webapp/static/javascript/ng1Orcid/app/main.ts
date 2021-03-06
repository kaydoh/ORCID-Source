import 'reflect-metadata';
import 'zone.js';

import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import {Router} from '@angular/router';
//import {UpgradeModule} from '@angular/upgrade/static';

import {Ng1AppModule} from './modules/ng1_app.ts';
import {Ng2AppModule} from './modules/ng2_app.ts';

//platformBrowserDynamic().bootstrapModule(Ng2AppModule).then(
  //  platformRef => {
        //const upgrade = platformRef.injector.get(UpgradeModule) as UpgradeModule;
        //upgrade.bootstrap(document.body, ['orcidApp'], {strictDi: true});
        
        //upgrade.bootstrap(document.body, [Ng1AppModule.name]);

        // bootstrap angular1
        //(<any>platformRef.instance).upgrade.bootstrap(document.body, [Ng1AppModule.name]);

        // setTimeout is necessary because upgrade.bootstrap is async.
        // This should be fixed.
        //setTimeout(
        //    () => {
        //        platformRef.injector.get(Router).initialNavigation();
        //    }, 
        //    0
        //);
    //}
//);

platformBrowserDynamic().bootstrapModule(Ng2AppModule).then(ref => {
  // bootstrap angular1
  (<any>ref.instance).upgrade.bootstrap(document.body, [Ng1AppModule.name]);

  // setTimeout is necessary because upgrade.bootstrap is async.
  // This should be fixed.
  //setTimeout(() => {
  //  ref.injector.get(Router).initialNavigation();
  //}, 0);
});