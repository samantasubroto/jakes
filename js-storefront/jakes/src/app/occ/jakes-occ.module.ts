import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { provideConfig } from "@spartacus/core";
import { jakesOccConfig } from "./config";

@NgModule({
  imports: [CommonModule],
  providers: [
    provideConfig(jakesOccConfig)
  ],
})
export class JakesOccModule { }