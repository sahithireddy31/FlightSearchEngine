import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { FlightSearchService, FlightSearchParams, Flights } from './flight-search.service';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.scss']
})
export class FlightComponent implements OnInit {
  public flightNumbers: Array<string> = [];
  public origins: Array<string> = [];
  public destinations: Array<string> = [];
  public results: Array<Flights>;
  public fdate: Date;
  public flightForm: FormGroup;
  public error;
  constructor(private flightService: FlightSearchService, private fb: FormBuilder) { }
  ngOnInit() {
    this.flightForm = this.fb.group({
      date: null,
      flightNumber: null,
      origin: null,
      destination: null
    });;
    this.flightService.getAllFlights().subscribe(flights => {
      this.flightNumbers = flights.map(n => n.flightNumber).filter((val, index, self) => self.indexOf(val) === index).sort();
      this.origins = flights.map(o => o.origin).filter((val, index, self) => self.indexOf(val) === index).sort();
      this.destinations = flights.map(d => d.destination).filter((val, index, self) => self.indexOf(val) === index).sort();
    });
  }
  Submit() {
    this.error = '';
    this.results = null;
    const flightParams = this.flightForm.value as FlightSearchParams;
    if (flightParams.date && ((flightParams.origin && flightParams.destination) || flightParams.flightNumber)) {
      flightParams.date = new Date(flightParams.date).toISOString().substring(0, 19);
      this.flightService.getFlightSearch(flightParams).subscribe(res => {
        this.results = res;
      });
    } else {
      this.error = 'Enter departure date, destination and flight number or origin';
    }
  }

  Clear() {
    this.flightForm.setValue({
      date: null,
      flightNumber: null,
      origin: null,
    destination: null
    });
    this.results = null;
  }
}
