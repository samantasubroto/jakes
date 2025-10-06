import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OccEndpointsService } from '@spartacus/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JakesProductService {

  constructor(
    private http: HttpClient,
    private occEndpoints: OccEndpointsService
  ) {}

  /**
   * Toggle liked status for a product
   * @param productCode The product code
   * @param isLiked true if liked, false otherwise
   */
  updateProductLikedStatus(productCode: string, isLiked: boolean): Observable<any> {
    // Build the OCC endpoint URL dynamically
    const url = this.occEndpoints.buildUrl(`products/liked/${productCode}`, {
      queryParams: { isLiked, fields: 'DEFAULT' }
    });

    return this.http.patch(url, {}); // empty body since your OCC endpoint doesn’t need one
  }
}
